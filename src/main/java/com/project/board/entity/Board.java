package com.project.board.entity;

import lombok.Data;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

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
    private Date bDate;
    private int bViews;
}
