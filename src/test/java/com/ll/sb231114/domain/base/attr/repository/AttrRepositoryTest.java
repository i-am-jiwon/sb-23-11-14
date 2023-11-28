package com.ll.sb231114.domain.base.attr.repository;

import com.ll.sb231114.domain.base.attr.entity.Attr;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback
public class AttrRepositoryTest {
    @Autowired
    private AttrRepository attrRepository;

    @DisplayName("attr저장")
    @Test
    void t1() {
        Attr attr = Attr.builder()
                .createDate(LocalDateTime.now())
                .name("Age")
                .build();
        attrRepository.save(attr);
        assertThat(attr.getId()).isGreaterThan(0L);
    }

    @DisplayName("attr저장 한 번 더")
    @Test
    void t2() {
        Attr attr = Attr.builder()
                .createDate(LocalDateTime.now())
                .name("Age")
                .build();
        attrRepository.save(attr);
        assertThat(attr.getId()).isGreaterThan(0L);
    }

    @DisplayName("attr select count(*) from attr")
    @Test
    void t3() {
        assertThat(attrRepository.count()).isEqualTo(0);
    }
}
