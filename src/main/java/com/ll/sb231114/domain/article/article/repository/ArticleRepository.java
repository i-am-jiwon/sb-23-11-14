package com.ll.sb231114.domain.article.article.repository;

import com.ll.sb231114.domain.article.article.entity.Article;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepository {
    private final List<Article> articles = new ArrayList<>();

    public Article save(Article article) {
        if(article.getId() == null){
            article.setId(articles.size() + 1L);
        }
        articles.add(article);

        return article;
    }

    public Article findLastArticle() {
        return articles.getLast();
    }

    public List<Article> findAll() {
        return articles;
    }
}
