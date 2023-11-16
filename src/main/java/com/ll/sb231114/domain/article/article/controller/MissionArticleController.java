package com.ll.sb231114.domain.article.article.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.sb231114.domain.article.article.entity.Article;
import com.ll.sb231114.domain.article.article.service.ArticleService;
import com.ll.sb231114.global.RsData;
import com.ll.sb231114.global.rq.Rq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MissionArticleController {


    private final ArticleService articleService;

    private final Rq rq;

    @GetMapping("/article/write")
    String write() {
        return "article/write";
    }

    @PostMapping("/article/write")
    @ResponseBody
    RsData<Article> write(
            String title,
            String body
    ) {

        Article article = articleService.write(title, body);

        RsData<Article> rs = new RsData(
                "S-1",
                "%d번 게시물 작성".formatted(article.getId()),
                article
        );
        return rs;
    }

    @SneakyThrows
    @PostMapping("/article/write2")
    void write2(
            HttpServletRequest req,
            HttpServletResponse resp
    ) {
        String title = req.getParameter("title");
        String body = req.getParameter("body");

        Article article = articleService.write(title, body);

        RsData<Article> rs = new RsData(
                "S-1",
                "%d번 게시물 작성".formatted(article.getId()),
                article
        );
        ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(objectMapper.writeValueAsString(rs));
    }



    @GetMapping("/article/getLastArticle")
    @ResponseBody
    Article getLastArticle(){
        return articleService.findLastArticle();
    }

    @GetMapping("/article/getArticles")
    @ResponseBody
    List<Article> getArticles(){
        return articleService.findAll();
    }

    @GetMapping("/article/articleServicePointer")
    @ResponseBody
    String articleServicePointer(){
        return articleService.toString();
    }

    @GetMapping("/article/httpServletRequestPointer")
    @ResponseBody
    String httpServletRequestPointer(HttpServletRequest req){
        return req.toString();
    }

    @GetMapping("/article/httpServletResponsePointer")
    @ResponseBody
    String httpServletResponsePointer(HttpServletResponse resp){
        return resp.toString();
    }

    @GetMapping("/article/rqPointer")
    @ResponseBody
    String rqPointer(){
        return rq.toString();
    }
}




