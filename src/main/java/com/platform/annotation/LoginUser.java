package com.platform.annotation;

import java.lang.annotation.*;

/**
 * 登录用户信息
 */
// 作用于方法里的参数上
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginUser {
}
