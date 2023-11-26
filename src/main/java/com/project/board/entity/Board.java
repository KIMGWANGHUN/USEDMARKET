package com.project.board.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private String bImage;
    private String bAddress;
    private String bDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private int bViews;

    public Board(){
        super();
    }

    public Board(String bTitle, String bCategory, String bContent, String bPrice, String bImage, String bAddress, String bDate, int bViews) {
        this.bTitle = bTitle;
        this.bCategory = bCategory;
        this.bContent = bContent;
        this.bPrice = bPrice;
        this.bImage = bImage;
        this.bAddress = bAddress;
        this.bDate = bDate;
        this.bViews = bViews;
    }
}
