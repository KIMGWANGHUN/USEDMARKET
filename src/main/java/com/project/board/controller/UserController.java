package com.project.board.controller;

import com.project.board.dto.UserDto;
import com.project.board.sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class UserController {
//생성자 주입
    private final UserService userService;
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/UserJoinPage")
    public ModelAndView userJoinPage() {
        ModelAndView mav = new ModelAndView("/UserJoinPage");
        return mav;
    }

    @GetMapping("/boardList")
    public ModelAndView boardList() {
        ModelAndView mav = new ModelAndView("/boardList");
        return mav;
    }

    @PostMapping("/save")
    public String save(UserDto uDto) {
        System.out.println("aa");
        System.out.println("test" + uDto);
        userService.save(uDto);
        return null;
    }
}
