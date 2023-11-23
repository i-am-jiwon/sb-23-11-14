package com.ll.sb231114.domain.member.member.controller;

import com.ll.sb231114.domain.member.member.entity.Member;
import com.ll.sb231114.domain.member.member.service.MemberService;
import com.ll.sb231114.global.rq.Rq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Validated
public class MemberController {


    private final MemberService memberService;
    private final Rq rq;


    @GetMapping("/member/login")
    String showLogin() {
        return "member/login";
    }

    @Data
    public static class LoginForm {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

    @PostMapping("/member/login")
    String login(@Valid LoginForm loginForm) {

        Member member = memberService.findByUsername(loginForm.username).get();

        if (!member.getPassword().equals(loginForm.password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        rq.setSessionAttr("loginedMemberId", member.getId());
        rq.setSessionAttr("authorities", member.getAuthorities());

        return rq.redirect("/article/list", "로그인 완료");

    }

    @GetMapping("/member/logout")
    String logout() {
        rq.removeSessionAttr("loginedMemberId");

        return rq.redirect("/article/list", "로그아웃");

    }


    @GetMapping("/member/join")
    String join() {
        return "member/join";
    }

    @Data
    public static class JoinForm {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

    @PostMapping("/member/join")
    String join(@Valid JoinForm joinForm) {

        memberService.join(joinForm.username, joinForm.password);

        return rq.redirect("/member/login", "가입완료");

    }


}




