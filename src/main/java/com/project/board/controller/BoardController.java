package com.project.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {

    @GetMapping("/board/write")
    public String boardWrite(){

        return "boardWrite";
    }

    @GetMapping("/board/list")
    public String boardList(){

        return "boardList";
    }
}