package com.project.board.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor //기본 생성자를 자동으로 생성
@ToString // toString 메서드 자동생성
public class UserDto {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String nickname;
    private String address;
    private String detailAddress;
}