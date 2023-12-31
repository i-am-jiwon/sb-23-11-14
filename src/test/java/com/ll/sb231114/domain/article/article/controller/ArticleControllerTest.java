package com.ll.sb231114.domain.article.article.controller;


import com.ll.sb231114.domain.article.article.entity.Article;
import com.ll.sb231114.domain.article.article.service.ArticleService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class ArticleControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ArticleService articleService;

    // GET /article/list
    @Test
    @DisplayName("게시물 목록 페이지를 보여준다")
    void t1() throws Exception {
        // WHEN
        ResultActions resultActions = mvc
                .perform(get("/article/list"))
                .andDo(print());

        // THEN
        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(handler().handlerType(MissionArticleController.class))
                .andExpect(handler().methodName("showList"))
                .andExpect(content().string(containsString("""
                        글 목록
                        """.stripIndent().trim())))
                .andExpect(content().string(containsString("""
                        3번 : member2
                        """.stripIndent().trim())))
                .andExpect(content().string(containsString("""
                        2번 : member1
                        """.stripIndent().trim())))
                .andExpect(content().string(containsString("""
                        1번 : admin
                        """.stripIndent().trim())));
    }

    @Test
    @DisplayName("게시물 내용 페이지를 보여준다")
    void t2() throws Exception {
        // WHEN
        ResultActions resultActions = mvc
                .perform(get("/article/detail/1"))
                .andDo(print());

        Article article = articleService.findById(1L).get();

        // THEN
        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(handler().handlerType(MissionArticleController.class))
                .andExpect(handler().methodName("showDetail"))
                .andExpect(content().string(containsString("""
                        글 내용
                        """.stripIndent().trim())))
                .andExpect(content().string(containsString("""
                        <div class="badge badge-outline">1</div>
                        """.stripIndent().trim())))
                .andExpect(content().string(containsString(article.getTitle())))
                .andExpect(content().string(containsString(article.getBody())));
    }

    // GET /article/detail/{id}
    // GET /article/write
    // POST /article/write
    // GET /article/modify/{id}
    // PUT /article/modify/{id}
    // DELETE /article/delete/{id}
}