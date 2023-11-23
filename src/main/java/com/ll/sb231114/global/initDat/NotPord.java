package com.ll.sb231114.global.initDat;

import com.ll.sb231114.domain.article.article.service.ArticleService;
import com.ll.sb231114.domain.member.member.service.MemberService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotPord {
    @Bean
    public ApplicationRunner initNotPord(
            MemberService memberService,
            ArticleService articleService
    ){
        return args -> {
            System.out.println("hi");
        };
    }
}
