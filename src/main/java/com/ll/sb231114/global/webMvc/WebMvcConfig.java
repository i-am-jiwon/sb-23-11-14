package com.ll.sb231114.global.webMvc;

import com.ll.sb231114.global.intercepter.NeedToAdminInterceptor;
import com.ll.sb231114.global.intercepter.NeedToLoginInterceptor;
import com.ll.sb231114.global.intercepter.NeedToLogoutInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final NeedToLoginInterceptor needToLoginInterceptor;
    private final NeedToLogoutInterceptor needToLogoutInterceptor;
    private final NeedToAdminInterceptor needToAdminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(needToLogoutInterceptor)
                .addPathPatterns("/member/login")
                .addPathPatterns("/member/findusername")
                .addPathPatterns("/member/findPassword")
                .addPathPatterns("member/join");
        registry.addInterceptor(needToLoginInterceptor)
                .addPathPatterns("/adm/**")
                .addPathPatterns("/article/write")
                .addPathPatterns("/article/modify/**")
                .addPathPatterns("/article/delete/**");
        registry.addInterceptor(needToAdminInterceptor)
                .addPathPatterns("/adm/**");
    }
}