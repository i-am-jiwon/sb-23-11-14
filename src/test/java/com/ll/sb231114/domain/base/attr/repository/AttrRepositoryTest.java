package com.ll.sb231114.domain.base.attr.repository;

import com.ll.sb231114.domain.article.article.repository.ArticleRepository;
import com.ll.sb231114.domain.base.attr.entity.Attr;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("attr저장")
public class AttrRepositoryTest {
    @Autowired
    private AttrRepository attrRepository;

    @DisplayName("t1")
    @Test
    void t1(){
        Attr attr = Attr.builder()
                .createDate(LocalDateTime.now())
                .name("Age")
                .build();
        attrRepository.save(attr);
        assertThat(attr.getId()).isGreaterThan(0L);
    }
}
