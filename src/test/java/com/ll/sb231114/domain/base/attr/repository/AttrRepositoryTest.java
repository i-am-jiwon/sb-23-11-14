package com.ll.sb231114.domain.base.attr.repository;

import com.ll.sb231114.domain.base.attr.entity.Attr;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class AttrRepositoryTest {
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
