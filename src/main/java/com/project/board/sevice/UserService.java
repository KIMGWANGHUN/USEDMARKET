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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private Member member;
    private PasswordEncoder passwordEncoder;
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


    //스프링 시큐리티에서 사용자 인증을 하기위해 사용되는 메서드
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = userRepository.findByEmail(email);
        if (member == null) {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }
        return  User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles("USER") // 권한 설정 (필요에 따라 변경)
                .build();
    }

    public Member myInfoView(String email) {
        Member member = userRepository.findByEmail(email);
        return member;
    }

    public void update(Member member,String email) {
        Member oriMember = userRepository.findByEmail(email);
        if(member.getPassword().isEmpty()) {
            member.setPassword(oriMember.getPassword());
        } else {
        userRepository.save(member);
        }
    }

}
