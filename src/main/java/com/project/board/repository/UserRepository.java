package com.project.board.repository;

import com.project.board.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> { // value값 자리에는 pk값의 타입을 입력
    Optional<UserEntity> findByemail(String email);
}
