package com.platform.service.impl;

import com.platform.common.RestResponse;
import com.platform.common.ResultEnum;
import com.platform.common.ResultUtil;
import com.platform.dao.CollectInfoMapper;
import com.platform.dao.DiseaseOmimMapper;
import com.platform.entity.req.CollectReq;
import com.platform.exception.BusinessException;
import com.platform.model.*;
import com.platform.service.CollectService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class CollectServiceImpl implements CollectService {
    @Autowired
    CollectInfoMapper collectInfoMapper;
    @Autowired
    DiseaseOmimMapper diseaseOmimMapper;

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
        if (!collectInfos.isEmpty()) {
            collectInfos.forEach(e -> {
                log.info("type:{},name{}",e.getType(),e.getName());
                if (e.getType() == 0 && isNumericzidai(e.getName())) {
                    DiseaseOmimExample diseaseOmimExample = new DiseaseOmimExample();
                    diseaseOmimExample.createCriteria().andOmimIdEqualTo(Integer.valueOf(e.getName()));
                    List<DiseaseOmim> diseaseOmims = diseaseOmimMapper.selectByExample(diseaseOmimExample);
                    if (!diseaseOmims.isEmpty()) {
                        e.setDiseaseName(diseaseOmims.get(0).getDiseaseName());
                    }
                }
                if (e.getType() == 0 && !isNumericzidai(e.getName())) {
                    DiseaseOmimExample diseaseOmimExample = new DiseaseOmimExample();
                    diseaseOmimExample.createCriteria().andDiseaseNameEqualTo(e.getName());
                    List<DiseaseOmim> diseaseOmims = diseaseOmimMapper.selectByExample(diseaseOmimExample);
                    if (!diseaseOmims.isEmpty()) {
                        e.setDiseaseName(diseaseOmims.get(0).getDiseaseName());
                        e.setName(String.valueOf(diseaseOmims.get(0).getOmimId()));
                    }
                }
            });
        }
        return ResultUtil.success(collectInfos);
    }

    public static boolean isNumericzidai(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
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
