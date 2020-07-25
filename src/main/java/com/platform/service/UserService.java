package com.platform.service;

import com.platform.common.RestResponse;
import com.platform.entity.req.*;
import com.platform.entity.resp.UserInfoResp;
import com.platform.model.UserInfo;

/**
 * @author shitou
 */
public interface UserService {

    UserInfo findByUserId(Integer userId);

    /**
     * 用户注册
     *
     * @param registeredReq
     * @return
     */
    RestResponse insertUser(RegisteredReq registeredReq);

    /**
     * 用户登录
     *
     * @param loginReq
     * @return
     */
    RestResponse userLogin(LoginReq loginReq);

    /**
     * 忘记密码
     *
     * @param forgetPasswordReq
     * @return
     */
    RestResponse forgetPassword(ForgetPasswordReq forgetPasswordReq);

    /**
     * 查询用户信息
     *
     * @param userInfo
     * @return
     */
    RestResponse<UserInfoResp> queryUserInfo(UserInfo userInfo);

    /**
     * 修改用户信息
     *
     * @param userInfo
     * @param userInfoReq
     * @return
     */
    RestResponse<UserInfoResp> updateUser(UserInfo userInfo, UserInfoReq userInfoReq);

    /**
     * 修改密码
     *
     * @param userInfo
     * @param updatePasswordReq
     * @return
     */
    RestResponse updatePassword(UserInfo userInfo, UpdatePasswordReq updatePasswordReq);
}
