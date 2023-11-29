package com.project.board.sevice;

import com.project.board.dto.UserDto;
import com.project.board.entity.Member;
import com.project.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    //회원가입
    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return userRepository.save(member);
    }

    //회원가입시 가입된 이메일 체크
    private void validateDuplicateMember(Member member) {
        Member findMember = userRepository.findByEmail(member.getEmail());
        if(findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = userRepository.findByEmail(email);
        if (member == null) {
            throw new UsernameNotFoundException(email + "에 해당하는 사용자를 찾을 수 없습니다.");
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword()) // 패스워드 인코딩이 필요하면 추가해야 합니다.
                .roles("USER") // 사용자의 권한을 설정하려면 추가해야 합니다.
                .build();
    }

    //로그인 처리 과정
}
