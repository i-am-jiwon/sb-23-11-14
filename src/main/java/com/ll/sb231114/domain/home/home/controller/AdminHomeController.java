package com.ll.sb231114.domain.home.home.controller;

import com.ll.sb231114.global.rq.Rq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminHomeController {
    private final Rq rq;

    @GetMapping("/adm")
    public String showMain(){
        if (!rq.isAdmin()){
            throw new RuntimeException("no");
        }
        return "home/home/adm/main";
    }

    @GetMapping("/adm/home/about")
    public String showAbout(){
        if (!rq.isAdmin()){
            throw new RuntimeException("no");
        }
        return "home/home/adm/main";
    }
}
