package com.project.board.controller;

import com.project.board.dto.UserDto;
import com.project.board.entity.Member;
import com.project.board.sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {
//생성자 주입
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
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



    @PostMapping(value="save")
    public String save(@Valid UserDto uDto, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            return "index";
        }

        try {
            Member member = Member.createMember(uDto, passwordEncoder);
            userService.saveMember(member);
        } catch(IllegalStateException e) {
            model.addAttribute("errorMsg",e.getMessage());
            return "index";
        }

        return "redirect:/";
    }

    @GetMapping(value ="/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginError","아이디 또는 비밀번호를 확인해주세요");
        return "redirect:/";
    }

    //판매글 페이지로 이동
    @GetMapping("/boardList")
    public ModelAndView boardList() {
        ModelAndView mav = new ModelAndView("/boardList");
        return mav;
    }
}
