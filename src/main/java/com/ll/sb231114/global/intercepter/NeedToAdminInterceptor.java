package com.ll.sb231114.global.intercepter;

import com.ll.sb231114.global.rq.Rq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NeedToAdminInterceptor implements HandlerInterceptor {

    private final Rq rq;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {


        if(!rq.isAdmin()){
            throw new RuntimeException("only admin use");
        }

        return true;
    }

}

