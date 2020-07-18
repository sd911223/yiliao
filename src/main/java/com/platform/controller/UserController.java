package com.platform.controller;

import com.platform.common.RestResponse;
import com.platform.common.ResultUtil;
import com.platform.entity.req.ForgetPasswordReq;
import com.platform.entity.req.LoginReq;
import com.platform.entity.req.RegisteredReq;
import com.platform.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(tags = "用户管理")
@RequestMapping("mtApi/")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 用户注册
     *
     * @param registeredReq
     * @return
     */
    @ApiOperation("注册用户")
    @PostMapping("/user/registered")
    public RestResponse insertUser(@RequestBody @Valid RegisteredReq registeredReq) {
        userService.insertUser(registeredReq);
        return ResultUtil.success();
    }

    /**
     * 用户登录
     *
     * @param loginReq
     * @return
     */
    @ApiOperation("用户登录")
    @PostMapping("/user/login")
    public RestResponse userLogin(@RequestBody @Valid LoginReq loginReq) {

        return userService.userLogin(loginReq);
    }

    /**
     * 忘记密码
     *
     * @param forgetPasswordReq
     * @return
     */
    @ApiOperation("忘记密码")
    @PostMapping("/user/forgetPassword")
    public RestResponse forgetPassword(@RequestBody @Valid ForgetPasswordReq forgetPasswordReq) {

        return userService.forgetPassword(forgetPasswordReq);
    }

}
