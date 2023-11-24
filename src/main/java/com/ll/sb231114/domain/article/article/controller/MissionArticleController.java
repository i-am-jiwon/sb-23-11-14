package com.ll.sb231114.domain.article.article.controller;

import com.ll.sb231114.domain.article.article.entity.Article;
import com.ll.sb231114.domain.article.article.service.ArticleService;
import com.ll.sb231114.domain.member.member.entity.Member;
import com.ll.sb231114.domain.member.member.service.MemberService;
import com.ll.sb231114.global.rq.Rq;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class MissionArticleController {


    private final ArticleService articleService;
    private final MemberService memberService;
    private final Rq rq;


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/article/detail/{id}")
    String showDetail(Model model, @PathVariable long id) {

        Article article = articleService.findById(id).get();

        model.addAttribute("article", article);

        return "article/detail";
    }


    @GetMapping("/article/delete/{id}")
    String write(@PathVariable long id) {
        Article article = articleService.findById(id).get();

        if (!articleService.canDelete(rq.getMember(), article)) {
            throw new RuntimeException("no authorities");
        }

        articleService.delete(article);

        return rq.redirect("/article/list", "%d번 게시물이 삭제되었습니다.".formatted(id));


    }


    @GetMapping("/article/modify/{id}")
    String modify(Model model, @PathVariable long id) {

        Article article = articleService.findById(id).get();

        if (!articleService.canModify(rq.getMember(), article)) {
            throw new RuntimeException("no authorities");
        }

        model.addAttribute("article", article);

        return "article/modify";
    }

    @Data
    public static class ModifyForm {
        @NotBlank
        private String title;
        @NotBlank
        private String body;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/article/modify/{id}")
    String modify(@PathVariable long id, @Valid ModifyForm ModifyForm) {

        Article article = articleService.findById(id).get();

        if (article == null) throw new RuntimeException("no article");
        if (!articleService.canModify(rq.getMember(), article)) {
            throw new RuntimeException("no authorities");
        }

        articleService.modify(article, ModifyForm.title, ModifyForm.body);


        return rq.redirect("/article/list", "%d번 게시물이 수정되었습니다.".formatted(id));

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/article/write")
    String write() {

        return "article/write";
    }

    @Data
    public static class WriteForm {
        @NotBlank
        private String title;
        @NotBlank
        private String body;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/article/write")
    @SneakyThrows
    String write(@Valid WriteForm writeForm) {

        Member loginedMember = rq.getMember();
        Article article = articleService.write(rq.getMember(), writeForm.title, writeForm.body);

        return rq.redirect("/article/list", "%d번 게시물 생성되었습니다.".formatted(article.getId()));

    }

    @GetMapping("/article/list")
    String showList(Model model) {

        Member loginedMember = memberService.findById(1L).get();
        List<Article> articles = articleService.findAll();


        model.addAttribute("articles", articles);
        model.addAttribute("loginedMember", loginedMember);
        return "article/list";
    }
}




