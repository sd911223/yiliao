package com.platform.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.platform.common.RestResponse;
import com.platform.common.ResultEnum;
import com.platform.common.ResultUtil;
import com.platform.dao.UserInfoMapper;
import com.platform.entity.enums.MailType;
import com.platform.entity.req.*;
import com.platform.entity.resp.UserInfoResp;
import com.platform.exception.BusinessException;
import com.platform.model.UserInfo;
import com.platform.model.UserInfoExample;
import com.platform.service.UserService;
import com.platform.util.JwtUtils;
import com.platform.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RedisUtil redisUtil;


    @Override
    public UserInfo findByUserId(Integer userId) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        return userInfo;
    }

    /**
     * 用户注册
     *
     * @param registeredReq
     */
    @Override
    public RestResponse insertUser(RegisteredReq registeredReq) {
        //校验参数
        verificationReq(registeredReq);
        //校验用户名/邮箱是否重复
        verificationRepeat(registeredReq);
        //添加入库
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(registeredReq, userInfo);
        userInfo.setCreateTime(new Date());
        userInfo.setIsAvailable(0);
        userInfo.setPassword(SecureUtil.md5(registeredReq.getPassword()));
        //入库
        userInfoMapper.insert(userInfo);
        //删除验证码
        redisUtil.delete(registeredReq.getEMail());
        log.info("注册成功!");
        return ResultUtil.success("注册成功!");
    }

    /**
     * 校验邮箱/用户名是否重复
     *
     * @param registeredReq
     */
    private void verificationRepeat(RegisteredReq registeredReq) {

        //加锁，防止并发情况下出现重名现象
        synchronized (this) {
            UserInfoExample infoExample = new UserInfoExample();
            infoExample.createCriteria().
                    andUserNameEqualTo(registeredReq.getUserName()).
                    andIsAvailableEqualTo(0);

            UserInfoExample mailExample =  new UserInfoExample();
            mailExample.createCriteria().
                    andEMailEqualTo(registeredReq.getEMail()).
                    andIsAvailableEqualTo(0);
            List<UserInfo> userInfos = userInfoMapper.selectByExample(infoExample);
            List<UserInfo> mailInfos = userInfoMapper.selectByExample(mailExample);
            //重名
            if (!userInfos.isEmpty()) {
                log.error(registeredReq.getUserName() + " 用户名重复。");
                throw new BusinessException(ResultEnum.USER_IS_EXISTS.getStatus(), ResultEnum.USER_IS_EXISTS.getMsg());
            }
            if (!mailInfos.isEmpty()) {
                log.error(registeredReq.getEMail() + " 邮箱重复。");
                throw new BusinessException(ResultEnum.EMAIL_IS_EXISTS.getStatus(), ResultEnum.EMAIL_IS_EXISTS.getMsg());
            }
        }
    }

    /**
     * 验证注册入参
     *
     * @param registeredReq
     */
    private void verificationReq(RegisteredReq registeredReq) {
        //校验密码是否一致
        if (!registeredReq.getPassword().equals(registeredReq.getAgainPassword())) {
            throw new BusinessException(ResultEnum.PASSWORD_NOT_IDENTICAL.getStatus(), ResultEnum.PASSWORD_NOT_IDENTICAL.getMsg());
        }
        //判断邮箱是否存在
        boolean hasKey = redisUtil.hasKey(registeredReq.getEMail() + ":" + MailType.REGISTERED);
        if (!hasKey) {
            throw new BusinessException(ResultEnum.VERIFICATION_ERROR.getStatus(), ResultEnum.VERIFICATION_ERROR.getMsg());
        }
        //判断验证码是否正确
        String mailCode = redisUtil.get(registeredReq.getEMail() + ":" + MailType.REGISTERED).toString();
        if (StringUtils.isBlank(mailCode) || null == mailCode) {
            throw new BusinessException(ResultEnum.VERIFICATION_ERROR.getStatus(), ResultEnum.VERIFICATION_ERROR.getMsg());
        }
        if (!registeredReq.getVerificationCode().equals(mailCode)) {
            throw new BusinessException(ResultEnum.VERIFICATION_ERROR.getStatus(), ResultEnum.VERIFICATION_ERROR.getMsg());
        }
    }

    /**
     * 用户登录
     *
     * @param loginReq
     * @return
     */
    @Override
    public RestResponse userLogin(LoginReq loginReq) {
        UserInfoExample infoExample = new UserInfoExample();
        infoExample.createCriteria().
                andIsAvailableEqualTo(0).
                andUserNameEqualTo(loginReq.getUserName()).
                andPasswordEqualTo(SecureUtil.md5(loginReq.getPassword()));
        //判断用户是否存在
        List<UserInfo> infoList = userInfoMapper.selectByExample(infoExample);
        if (infoList.isEmpty()) {
            log.error(loginReq.getUserName() + " 用户不存在。");
            throw new BusinessException(ResultEnum.USER_NOT_EXIST.getStatus(), ResultEnum.USER_NOT_EXIST.getMsg());
        }
        //存在的话生成token
        String token = jwtUtils.generateToken(infoList.get(0));

        return ResultUtil.success(token);
    }

    /**
     * 忘记密码
     *
     * @param forgetPasswordReq
     * @return
     */
    @Override
    public RestResponse forgetPassword(ForgetPasswordReq forgetPasswordReq) {
        verificationFP(forgetPasswordReq);
        UserInfo userInfo = getUserInfo(forgetPasswordReq.getEMail());
        userInfo.setPassword(SecureUtil.md5(forgetPasswordReq.getAgainPassword()));
        userInfoMapper.updateByPrimaryKey(userInfo);
        return ResultUtil.success("密码修改成功!");
    }


    /**
     * 通过邮箱查询用户
     *
     * @param email
     * @return
     */
    private UserInfo getUserInfo(String email) {
        UserInfoExample infoExample = new UserInfoExample();
        infoExample.createCriteria().andEMailEqualTo(email).andIsAvailableEqualTo(0);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(infoExample);
        if (userInfos.isEmpty()) {
            log.error(email + " 邮箱不存在!");
            throw new BusinessException(ResultEnum.EMAIL_NOT_EXISTS.getStatus(), ResultEnum.EMAIL_NOT_EXISTS.getMsg());
        }
        return userInfos.get(0);
    }

    /**
     * 验证忘记密码入参
     *
     * @param forgetPasswordReq
     */
    private void verificationFP(ForgetPasswordReq forgetPasswordReq) {
        if (!forgetPasswordReq.getPassword().equals(forgetPasswordReq.getAgainPassword())) {
            throw new BusinessException(ResultEnum.PASSWORD_NOT_IDENTICAL.getStatus(), ResultEnum.PASSWORD_NOT_IDENTICAL.getMsg());
        }
        //判断邮箱是否存在
        boolean hasKey = redisUtil.hasKey(forgetPasswordReq.getEMail() + ":" + MailType.FORGET_PASSWORD);
        if (!hasKey) {
            throw new BusinessException(ResultEnum.VERIFICATION_ERROR.getStatus(), ResultEnum.VERIFICATION_ERROR.getMsg());
        }
        //判断验证码是否正确
        String mailCode = redisUtil.get(forgetPasswordReq.getEMail() + ":" + MailType.FORGET_PASSWORD).toString();
        if (StringUtils.isBlank(mailCode) || null == mailCode) {
            throw new BusinessException(ResultEnum.VERIFICATION_ERROR.getStatus(), ResultEnum.VERIFICATION_ERROR.getMsg());
        }
        if (!forgetPasswordReq.getVerificationCode().equals(mailCode)) {
            throw new BusinessException(ResultEnum.VERIFICATION_ERROR.getStatus(), ResultEnum.VERIFICATION_ERROR.getMsg());
        }
    }

    /**
     * 查询用户信息
     *
     * @param userInfo
     * @return
     */
    @Override
    public RestResponse<UserInfoResp> queryUserInfo(UserInfo userInfo) {
        UserInfo info = userInfoMapper.selectByPrimaryKey(userInfo.getUserId());
        UserInfoResp userInfoResp = new UserInfoResp();
        BeanUtils.copyProperties(info, userInfoResp);
        return ResultUtil.success(userInfoResp);
    }

    /**
     * 修改用户信息
     *
     * @param userInfo
     * @param userInfoReq
     * @return
     */
    @Override
    public RestResponse<UserInfoResp> updateUser(UserInfo userInfo, UserInfoReq userInfoReq) {
        UserInfo info = userInfoMapper.selectByPrimaryKey(userInfo.getUserId());
        //判断邮箱是否相同,不同的话 查看是否存在
        if (!info.geteMail().equals(userInfoReq.getEMail())) {
            UserInfoExample infoExample = new UserInfoExample();
            infoExample.createCriteria().andIsAvailableEqualTo(0).andEMailEqualTo(userInfoReq.getEMail());
            List<UserInfo> userInfos = userInfoMapper.selectByExample(infoExample);
            if (!userInfos.isEmpty()) {
                log.error("用户修改信息,邮箱已将已将存在,邮箱{}", userInfoReq.getEMail());
                throw new BusinessException(ResultEnum.EMAIL_IS_EXISTS.getStatus(), ResultEnum.EMAIL_IS_EXISTS.getMsg());
            }
        }
        BeanUtils.copyProperties(userInfoReq, info);
        userInfoMapper.updateByPrimaryKey(info);
        log.info("用户修改信息成功");
        return ResultUtil.success();
    }

    /**
     * 修改密码
     *
     * @param userInfo
     * @param updatePasswordReq
     * @return
     */
    @Override
    public RestResponse updatePassword(UserInfo userInfo, UpdatePasswordReq updatePasswordReq) {
        UserInfo primaryKey = userInfoMapper.selectByPrimaryKey(userInfo.getUserId());
        if (!primaryKey.getPassword().equals(SecureUtil.md5(updatePasswordReq.getPrimevalPassword()))) {
            throw new BusinessException(ResultEnum.PRIMEVAL_PASSWORD_ERROR.getStatus(), ResultEnum.PRIMEVAL_PASSWORD_ERROR.getMsg());
        }
        if (!updatePasswordReq.getNewPassword().equals(updatePasswordReq.getAgainPassword())) {
            throw new BusinessException(ResultEnum.PASSWORD_NOT_IDENTICAL.getStatus(), ResultEnum.PASSWORD_NOT_IDENTICAL.getMsg());
        }
        primaryKey.setPassword(SecureUtil.md5(updatePasswordReq.getNewPassword()));
        userInfoMapper.updateByPrimaryKey(primaryKey);
        return ResultUtil.success("修改密码成功!");
    }
}
