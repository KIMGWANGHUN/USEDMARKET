package com.project.board.entity;

import com.project.board.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name ="USERDATA")
public class UserEntity {
    @Id //PK 지정
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true) //제약조건 추가
    private String email;
    @Column
    private String name;

    @Column
    private String password;
    @Column
    private String phone;
    @Column
    private String nickname;
    @Column
    private String address;

    public static UserEntity toUserEntity(UserDto uDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(uDto.getEmail());
        userEntity.setEmail(uDto.getEmail());
        userEntity.setPassword(uDto.getPassword());
        userEntity.setPhone(uDto.getPhone());
        userEntity.setNickname(uDto.getNickname());
        userEntity.setAddress(uDto.getAddress());

        return userEntity;
    }
}
