package com.ll.sb231114.domain.article.article.controller;

import com.ll.sb231114.domain.article.article.entity.Article;
import com.ll.sb231114.domain.article.article.service.ArticleService;
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

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Validated
public class MissionArticleController {


    private final ArticleService articleService;


    @GetMapping("/article/detail/{id}")
    String showDetail(Model model, @PathVariable long id) {
        Article article = articleService.findById(id).get();

        model.addAttribute("article", article);

        return "article/detail";
    }


    @GetMapping("/article/delete/{id}")
    String write(@PathVariable long id) {
        articleService.delete(id);

        String msg = "%d번 게시물이 삭제되었습니다.".formatted(id);

        msg = URLEncoder.encode(msg, StandardCharsets.UTF_8);

        return "redirect:/article/list?msg=" + msg;
    }


    @GetMapping("/article/modify/{id}")
    String modify(Model model, @PathVariable long id) {
        Article article = articleService.findById(id).get();

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

    @PostMapping("/article/modify/{id}")
    String modify(@PathVariable long id, @Valid ModifyForm ModifyForm) {

        articleService.modify(id, ModifyForm.title, ModifyForm.body);

        String msg = "%d번 게시물이 수정되었습니다.".formatted(id);


        msg = URLEncoder.encode(msg, StandardCharsets.UTF_8);

        return "redirect:/article/list?msg=" + msg;
    }

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

    @PostMapping("/article/write")
    String write(@Valid WriteForm writeForm) {

        Article article = articleService.write(writeForm.title, writeForm.body);

        String msg = "%d번 게시물 생성되었습니다.".formatted(article.getId());

        msg = URLEncoder.encode(msg, StandardCharsets.UTF_8);

        return "redirect:/article/list?msg=" + msg;
    }

    @GetMapping("/article/list")
    String showList(Model model) {
        List<Article> articles = articleService.findAll();


        model.addAttribute("articles", articles);
        return "article/list";
    }
}




