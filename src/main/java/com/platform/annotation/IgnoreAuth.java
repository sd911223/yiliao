
package com.platform.annotation;

import java.lang.annotation.*;

/**
 * app登录效验
 * 过滤非登陆态
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreAuth {

}
