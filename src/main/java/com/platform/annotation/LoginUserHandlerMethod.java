package com.platform.annotation;


import com.platform.exception.BusinessException;
import com.platform.model.UserInfo;
import com.platform.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 */
@Component
@Slf4j
public class LoginUserHandlerMethod implements HandlerMethodArgumentResolver {
    private final String AUTH_TOKEN = "token";

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(UserInfo.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public UserInfo resolveArgument(MethodParameter methodParameter,
                                    ModelAndViewContainer modelAndViewContainer,
                                    NativeWebRequest nativeWebRequest,
                                    WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String token = request.getHeader(AUTH_TOKEN);
        if (StringUtils.isEmpty(token)) {
            throw new BusinessException(HttpStatus.UNAUTHORIZED.value(), "token失效，请重新登录");
        }
        UserInfo user = jwtUtils.getUser(token);
        return user;
    }
}
