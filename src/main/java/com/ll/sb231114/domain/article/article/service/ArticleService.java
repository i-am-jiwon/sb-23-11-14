package com.ll.sb231114.domain.article.article.service;

import com.ll.sb231114.domain.article.article.entity.Article;
import com.ll.sb231114.domain.article.article.repository.ArticleRepository;
import com.ll.sb231114.domain.member.member.entity.Member;
import com.ll.sb231114.global.rq.Rq;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;



    public Article write(Member author, String title, String body) {
        Article article = new Article(author, title, body);

        articleRepository.save(article);

        return article;
    }

    public Article findLastArticle() {
        return articleRepository.findLastArticle();
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }


    public Optional<Article> findById(long id) {
        return articleRepository.findById(id);
    }

    public void delete(long id) {
        articleRepository.delete(id);
    }

    public void modify(Article article, String title, String body) {
        article.setTitle(title);
        article.setBody(body);
    }

    public boolean canModify(Member actor, Article article) {
        return article.getAuthor().equals(actor);
    }
}
