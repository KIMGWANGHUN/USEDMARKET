package com.project.board.sevice;

import com.project.board.dto.UserDto;
import com.project.board.entity.UserEntity;
import com.project.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(UserDto uDto) {

        userRepository.findByemail(uDto.getEmail())
                .ifPresent(email -> {
                    throw new RuntimeException(email+"는 가입된 이메일입니다.");
                });
        //1. dto 객체를 -> entity 객체로 변환
        UserEntity userEntity = UserEntity.toUserEntity(uDto);
        userRepository.save(userEntity);
        //2.  repository에서 해당하는 메서드 호출
    }
}
