package com.project.board.repository;

import com.project.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, String> { // value값 자리에는 pk값의 타입을 입력

    //이메일값 조회
    Member findByEmail(String email);
    
    //유저의 닉네임값 조회
    Optional<Member> findByNickname(String nickname);

}
