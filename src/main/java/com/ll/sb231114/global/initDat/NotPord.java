package com.ll.sb231114.global.initDat;

import com.ll.sb231114.domain.article.article.service.ArticleService;
import com.ll.sb231114.domain.member.member.entity.Member;
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
            Member memberAdmin = memberService.join("admin", "1");
            Member memberUser1 = memberService.join("member1", "1");
            Member memberUser2 = memberService.join("member2", "1");

            articleService.write(memberAdmin, "admin", "aadd");
            articleService.write(memberUser1, "member1", "111");
            articleService.write(memberUser2, "member2", "222");
        };
    }
}
