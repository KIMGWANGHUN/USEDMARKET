package com.project.board.repository;

import com.project.board.dto.UserDto;
import com.project.board.entity.Board;
import com.project.board.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, String> { // value값 자리에는 pk값의 타입을 입력

    //이메일값 조회
    Member findByEmail(String email);

    //유저의 닉네임값 조회
    Optional<Member> findByNickname(String nickname);

    @Query("SELECT m FROM USERDATA m WHERE m.name = :name AND m.phone = :phone")
    Member findByNameAndPhone(@Param("name") String name, @Param("phone") String phone);


}