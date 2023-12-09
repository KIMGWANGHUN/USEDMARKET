package com.project.board.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bId;
    private String bTitle;
    private String bCategory;
    private String bContent;
    private String bPrice;
    private String bAddress;
    private String bDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private int bViews;
    private String fileName;
    private String filePath;

    public Board(){
        super();
    }

    public Board(String bTitle, String bCategory, String bContent, String bPrice, String bAddress, String bDate, int bViews) {
        this.bTitle = bTitle;
        this.bCategory = bCategory;
        this.bContent = bContent;
        this.bPrice = bPrice;
        this.bAddress = bAddress;
        this.bDate = bDate;
        this.bViews = bViews;
    }
}
