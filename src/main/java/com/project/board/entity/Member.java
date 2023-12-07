package com.project.board.entity;

import com.project.board.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity(name = "USERDATA")
@Setter
@Getter
@Table(name ="USERDATA")
public class Member   {
    @Id //PK 지정
    @Column(unique = true) //제약조건 추가
    private String email;
    private String name;
    private String password;
    private String phone;
    private String nickname;
    private String address;

    public static Member createMember(UserDto uDto, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setEmail(uDto.getEmail());
        member.setName((uDto.getName()));
        String password = passwordEncoder.encode(uDto.getPassword());
        member.setPassword(password);
        member.setPhone(uDto.getPhone());
        member.setNickname(uDto.getNickname());
        member.setAddress(uDto.getAddress());
        return member;
    }

    public static Member updateMember(UserDto uDto, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setEmail(uDto.getEmail());
        member.setName((uDto.getName()));
        String password = passwordEncoder.encode(uDto.getPassword());
        member.setPassword(password);
        member.setPhone(uDto.getPhone());
        member.setNickname(uDto.getNickname());
        member.setAddress(uDto.getAddress());
        return member;
    }

    public static Member findEmail(UserDto uDto) {
        Member member = new Member();
        member.setName(uDto.getName());
        member.setPhone(uDto.getPhone());
        return member;
    }


}