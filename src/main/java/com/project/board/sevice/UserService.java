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

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return userRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = userRepository.findByEmail(member.getEmail());
        if(findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Member member =userRepository.findByEmail(email);
        if (member==null){
            throw new UsernameNotFoundException(email);//<-로그인할 유저의 이메일을 파리미터로 전달 받는다.
        }
        return User.builder()//,<- 추가
                .username(member.getEmail())
                .password(member.getPassword())
                .build();

    }
}
