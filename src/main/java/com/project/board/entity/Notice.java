package com.project.board.entity;

import com.project.board.utill.NoticeType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Notice {
    @Id @Generated @Column(name = "notice_id")
    private long id;
    private String title;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    @Enumerated(EnumType.STRING)
    private NoticeType noticeType;
}
