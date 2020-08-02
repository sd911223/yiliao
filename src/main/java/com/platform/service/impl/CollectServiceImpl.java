package com.platform.service.impl;

import com.platform.common.RestResponse;
import com.platform.common.ResultEnum;
import com.platform.common.ResultUtil;
import com.platform.dao.CollectInfoMapper;
import com.platform.entity.req.CollectReq;
import com.platform.exception.BusinessException;
import com.platform.model.CollectInfo;
import com.platform.model.CollectInfoExample;
import com.platform.model.UserInfo;
import com.platform.service.CollectService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CollectServiceImpl implements CollectService {
    @Autowired
    CollectInfoMapper collectInfoMapper;

    /**
     * 添加收藏
     *
     * @param collectReq
     * @return
     */
    @Override
    public RestResponse addCollect(UserInfo userInfo, CollectReq collectReq) {
        CollectInfoExample collectInfoExample = new CollectInfoExample();
        collectInfoExample.createCriteria().andNameEqualTo(collectReq.getName()).andIsEffectiveEqualTo(1);
        List<CollectInfo> collectInfos = collectInfoMapper.selectByExample(collectInfoExample);
        if (!collectInfos.isEmpty()) {
            log.error("收藏已将存在,收藏name{}!", collectReq.getName());
            throw new BusinessException(ResultEnum.COLLECT_IS_EXIST.getStatus(), ResultEnum.COLLECT_IS_EXIST.getMsg());
        }
        CollectInfo collectInfo = new CollectInfo();
        BeanUtils.copyProperties(collectReq, collectInfo);
        collectInfo.setCreateTime(new Date());
        collectInfo.setIsEffective(1);
        collectInfo.setType(Integer.valueOf(collectReq.getType()));
        collectInfo.setUserId(userInfo.getUserId());
        collectInfoMapper.insertSelective(collectInfo);
        return ResultUtil.success(collectInfo);
    }

    /**
     * 用户收藏列表
     *
     * @param userInfo
     * @return
     */
    @Override
    public RestResponse listCollect(UserInfo userInfo, String nameAsc, String timeAsc) {
        CollectInfoExample collectInfoExample = new CollectInfoExample();
        collectInfoExample.createCriteria().andUserIdEqualTo(userInfo.getUserId()).andIsEffectiveEqualTo(1);
        if (StringUtils.isNotBlank(nameAsc)) {
            collectInfoExample.setOrderByClause("name DESC");
        }
        if (StringUtils.isNotBlank(timeAsc)) {
            collectInfoExample.setOrderByClause("create_time DESC");
        }
        List<CollectInfo> collectInfos = collectInfoMapper.selectByExample(collectInfoExample);
        return ResultUtil.success(collectInfos);
    }

    /**
     * 删除收藏
     *
     * @param id
     * @return
     */
    @Override
    public RestResponse deleteCollect(Integer id) {
        CollectInfo collectInfo = collectInfoMapper.selectByPrimaryKey(id);
        if (null == collectInfo) {
            log.error("收藏不存在,收藏ID{}!", id);
            throw new BusinessException(ResultEnum.ID_NOT_EXISTS.getStatus(), ResultEnum.ID_NOT_EXISTS.getMsg());
        }
        collectInfo.setIsEffective(2);
        collectInfoMapper.updateByPrimaryKey(collectInfo);
        return ResultUtil.success();
    }

    /**
     * 修改备注
     *
     * @param id
     * @param remark
     * @return
     */
    @Override
    public RestResponse updateCollect(Integer id, String remark) {
        CollectInfo collectInfo = collectInfoMapper.selectByPrimaryKey(id);
        if (null == collectInfo) {
            log.error("收藏不存在,收藏ID{}!", id);
            throw new BusinessException(ResultEnum.ID_NOT_EXISTS.getStatus(), ResultEnum.ID_NOT_EXISTS.getMsg());
        }
        collectInfo.setReservedTow(remark);
        collectInfoMapper.updateByPrimaryKey(collectInfo);
        return ResultUtil.success();
    }
}
