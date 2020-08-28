package com.platform.interceptor;

import com.platform.annotation.LoginUserHandlerMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Spring MVC 的辅助配置, 用来注册拦截器.
 *
 * @author shitou
 */
@Configuration
public class DefaultWebMvcConfigurerAdapter implements WebMvcConfigurer {
    @Autowired
    LoginUserHandlerMethod loginUserHandlerMethod;

    @Bean
    public AuthorizationInterceptor getDefaultWebMvcConfigurerAdapter() {
        return new AuthorizationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 访问权限控制
        registry.addInterceptor(getDefaultWebMvcConfigurerAdapter())
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**",
                        "/mtApi/user/registered",
                        "/mtApi/user/login",
                        "/mtApi/send/captcha",
                        "/mtApi/file/upload",
                        "/mtApi/file/show",
                        "/mtApi/user/forgetPassword",
                        "/mtApi/variation/**", "/mtApi/gene/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserHandlerMethod);
    }
}
