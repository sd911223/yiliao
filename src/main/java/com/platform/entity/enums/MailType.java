package com.platform.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 验证码类型
 *
 * @author shitou
 */
@AllArgsConstructor
@Getter
public enum MailType {

    REGISTERED("registered", "注册"),

    FORGET_PASSWORD("forgetPassword", "忘记密码"),

    LEAVE_A_MESSAGE("forgetPassword", "留言板");

    // 成员变量
    private String code;

    private String name;
}
