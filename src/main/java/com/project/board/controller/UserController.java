package com.project.board.controller;

import com.project.board.dto.UserDto;
import com.project.board.entity.Member;
import com.project.board.repository.UserRepository;
import com.project.board.sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Mod10Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    //생성자 주입
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    //메인페이지
    @GetMapping("/")
    public String index() {
        return "index";
    }

    //회원가입 페이지로 이동
    @GetMapping("/UserJoinPage")
    public ModelAndView userJoinPage() {
        ModelAndView mav = new ModelAndView("/UserJoinPage");
        return mav;
    }


    //회원가입
    @PostMapping(value = "save")
    public String save(@Valid UserDto uDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "index";
        }

        try {
            Member member = Member.createMember(uDto, passwordEncoder);
            userService.saveMember(member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "index";
        }

        return "redirect:/";
    }

    //마이페이지
    @GetMapping("/mypage")
    public String mypage(@AuthenticationPrincipal UserService userService, Model model) {
        model.addAttribute("member", userService);
        return "mypage";
    }

    //판매글 페이지로 이동
    @GetMapping("/boardList")
    public ModelAndView boardList() {
        ModelAndView mav = new ModelAndView("/boardList");
        return mav;
    }
}
