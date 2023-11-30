package com.project.board.sevice;

import com.project.board.entity.Notice;
import com.project.board.repository.NoticeRepository;
import com.project.board.utill.NoticeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@TestConfiguration
class NoticeServiceTest {
    @Autowired
    NoticeRepository noticeRepository;
    @Autowired
    NoticeService noticeService;
    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("notice 생성")
    void createNotice(){
        //given
        String title = "t1";
        String content = "c1";
        Notice notice = Notice.builder()
                .title(title)
                .content(content)
                .noticeType(NoticeType.NOTICE)
                .regDate(LocalDateTime.now())
                .modDate(LocalDateTime.now())
                .build();
        //when
        Notice pNotice = noticeService.createNotice(notice);
        //then
        assertEquals(notice.getTitle(), pNotice.getTitle());
        assertEquals(notice.getContent(), pNotice.getContent());
        assertThat(pNotice).isNotNull();
    }
}