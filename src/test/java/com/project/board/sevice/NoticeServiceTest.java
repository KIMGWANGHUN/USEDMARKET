package com.project.board.sevice;

import com.project.board.entity.Member;
import com.project.board.entity.Notice;
import com.project.board.repository.NoticeRepository;
import com.project.board.utill.NoticeType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@TestConfiguration
class NoticeServiceTest {
    @Autowired
    NoticeRepository noticeRepository;
    @Autowired
    NoticeService noticeService;
    @Autowired
    EntityManager entityManager;
    @Autowired
    UserService userService;

    MockMvc mockMvc;

    private Member user = getUser(
            "common user",
            "010-1111-2222",
            "123a",
            "aaa@aaa.com",
            "서울");

    @Test
    @DisplayName("notice 생성")
    void createNotice() throws Exception {
//        userService.saveMember(user);
//        mockMvc.perform(post("/login")
//                .param("email",user.getEmail())
//                .param("password",user.getPassword())
//        );
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


    private Member getUser(String userName, String userPhone, String password, String userId, String address){
        Member tempMember = new Member();
        tempMember.setName(userName);
        tempMember.setPhone(userPhone);
        tempMember.setPassword(password);
        tempMember.setEmail(userId);
        tempMember.setAddress(address);
        return tempMember;
    }
}