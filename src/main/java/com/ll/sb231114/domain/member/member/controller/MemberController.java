package com.ll.sb231114.domain.member.member.controller;

import com.ll.sb231114.domain.member.member.entity.Member;
import com.ll.sb231114.domain.member.member.service.MemberService;
import com.ll.sb231114.global.rq.Rq;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Validated
public class MemberController {


    private final MemberService memberService;
    private final Rq rq;


    @Data
    public static class ModifyForm {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

    @GetMapping("/member/login")
    String showLogin() {
        return "member/login";
    }


    @GetMapping("/member/join")
    String join() {
        return "member/join";
    }

    @Data
    public static class WriteForm {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

    @PostMapping("/member/join")
    String join(@Valid WriteForm joinForm) {

        Member member = memberService.join(joinForm.username, joinForm.password);

        return rq.redirect("/member/login" , "가입완료");

    }


}




