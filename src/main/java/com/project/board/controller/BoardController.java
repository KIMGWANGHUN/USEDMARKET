package com.project.board.controller;

import com.project.board.entity.Board;
import com.project.board.entity.Member;
import com.project.board.repository.BoardRepository;
import com.project.board.sevice.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    @Autowired
    private final BoardService boardService;

    //게시글 작성 페이지
    @GetMapping("/board/write")     //localhost:8090/board/write
    public String boardWriterForm(){
        return "boardWrite";
    }

    //게시물 등록
    @PostMapping("/board/writeEnd")
    public String boardWriteEnd(Board board, Model model){
        boardService.write(board);
        model.addAttribute("message", "작성완료!!");
        model.addAttribute("listPage", "/board/list");
        return "boardMessage";
    }

    //게시판 리스트
    @GetMapping("board/list")
    public String boardList(Model model){
        model.addAttribute("list", boardService.boardList());
        return "boardList";
    }

    //게시물 페이지
    @GetMapping("/board/view")  // localhost:8090/board/view?id=1
    @Transactional
    public String boardView(Model model, Integer id){
        model.addAttribute("board", boardService.boardView(id));
        return "boardView";
    }

    //게시글 삭제
    @GetMapping("/board/delete")
    public String boardDelete(Integer id){
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    //게시글 수정 페이지
    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model){
        model.addAttribute("board", boardService.boardView(id));
        return "boardModify";
    }

    //게시글 수정
    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board){
        Board boardTemp = boardService.boardView(id);
        boardTemp.setBCategory(board.getBCategory());
        boardTemp.setBTitle(board.getBTitle());
        boardTemp.setBPrice(board.getBPrice());
        boardTemp.setBImage(board.getBImage());
        boardTemp.setBContent(board.getBContent());

        boardService.write(boardTemp);

        return "redirect:/board/list";
    }
}