package com.platform.service;

import com.platform.common.RestResponse;
import com.platform.entity.req.ForgetPasswordReq;
import com.platform.entity.req.LoginReq;
import com.platform.entity.req.RegisteredReq;
import com.platform.entity.req.UserInfoReq;
import com.platform.entity.resp.UserInfoResp;
import com.platform.model.UserInfo;

public interface UserService {

    UserInfo findByUserId(Integer userId);

    RestResponse insertUser(RegisteredReq registeredReq);

    RestResponse userLogin(LoginReq loginReq);

    RestResponse forgetPassword(ForgetPasswordReq forgetPasswordReq);

    RestResponse<UserInfoResp> queryUserInfo(UserInfo userInfo);

    RestResponse<UserInfoResp> updateUser(UserInfo userInfo, UserInfoReq userInfoReq);
}
