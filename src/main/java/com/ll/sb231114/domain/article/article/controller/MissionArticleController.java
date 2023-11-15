package com.ll.sb231114.domain.article.article.controller;

import com.ll.sb231114.domain.article.article.entity.Article;
import com.ll.sb231114.domain.article.article.service.ArticleService;
import com.ll.sb231114.global.RsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MissionArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/article/write")
    String write() {
        return "article/write";
    }

    @PostMapping("/article/write")
    @ResponseBody
    RsData<Article> write(String title, String body) {
        Article article = articleService.write(title, body);

        RsData<Article> rs = new RsData(
                "S-1",
                "%d번 게시물 작성".formatted(article.getId()),
                article
        );
        return rs;
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

}




