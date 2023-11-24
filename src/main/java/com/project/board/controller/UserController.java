package com.project.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class UserController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/UserJoinPage")
    public ModelAndView userJoinPage() {
        ModelAndView mav = new ModelAndView("/UserJoinPage");
        return mav;
    }
}
