package com.platform.interceptor;

import com.platform.annotation.IgnoreAuth;
import com.platform.exception.BusinessException;
import com.platform.util.JwtUtils;
import com.platform.util.RedisUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户拦截器
 *
 * @author shitou
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        IgnoreAuth annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
        } else {
            return true;
        }

        // 如果有@IgnoreAuth注解，则不验证token
        if (annotation != null) {
            return true;
        }

        // 获取用户凭证
        String token = request.getHeader(jwtUtils.getHeader());
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(jwtUtils.getHeader());
        }

        // 凭证为空
        if (StringUtils.isEmpty(token)) {
            throw new BusinessException(HttpStatus.UNAUTHORIZED.value(), jwtUtils.getHeader() + "不能为空");
        }

        Claims claims = jwtUtils.getClaimByToken(token);
        if (claims == null || jwtUtils.isTokenExpired(claims.getExpiration())) {
            throw new BusinessException(HttpStatus.UNAUTHORIZED.value(), jwtUtils.getHeader() + "失效，请重新登录");
        }

        // 设置userId到request里，后续根据userId，获取用户信息
//        request.setAttribute(USER_KEY, Integer.valueOf(claims.getSubject()));

        return true;
    }
}
