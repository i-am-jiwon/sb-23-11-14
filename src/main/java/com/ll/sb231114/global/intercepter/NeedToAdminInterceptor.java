package com.ll.sb231114.global.intercepter;

import com.ll.sb231114.global.rq.Rq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class NeedToAdminInterceptor implements HandlerInterceptor {

    private final Rq rq;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!rq.isLogined()){
            throw new RuntimeException("please login");
        }

        if (!rq.isAdmin()){
            throw new RuntimeException("no admin");
        }

        return true;
    }

}

