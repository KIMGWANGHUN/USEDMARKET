package com.project.board.controller;

import com.project.board.entity.Board;
import com.project.board.entity.Member;
import com.project.board.repository.BoardRepository;
import com.project.board.repository.UserRepository;
import com.project.board.sevice.BoardService;
import com.project.board.sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.multi.MultiLabelUI;
import java.awt.font.MultipleMaster;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    @Autowired
    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final UserService userService;
    private final UserRepository userRepository;


    //게시글 작성 페이지
    @GetMapping("/board/write")     //localhost:8090/board/write
    public String boardWriterForm(Principal principal, Model model) {
        String nickname = principal.getName();
        Member member = userRepository.findByEmail(nickname);
        model.addAttribute("member", member);
        return "boardWrite";
    }

    //게시물 등록
    @PostMapping("/board/writeEnd")
    public String boardWriteEnd(Board board, Model model, MultipartFile file) throws Exception {

        boardService.write(board, file);

        model.addAttribute("message", "작성완료!!");

        model.addAttribute("listPage", "/board/list");

        return "boardMessage";
    }

    //게시판 리스트
    @GetMapping("/board/list")
    public String boardList(Model model, @PageableDefault(page = 0, size = 5, sort = "bId", direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword, Board board) {

        Page<Board> list = null;

        //검색없이 리스트를 보여줄때
        if (searchKeyword == null && StringUtils.isEmpty(board.getBCategory())) {
            list = boardService.boardList(pageable);

        //전체검색인 경우
        } else if (StringUtils.isEmpty(board.getBCategory())) {

            list = userService.boardSearchList(searchKeyword, pageable);

        //카테고리를 정하고 검색한 경우
        } else {
            list = userService.boardSearchList(searchKeyword, pageable);

            List<Board> searchResultContent = list.getContent();

            for (Board searchResultBoard : searchResultContent) {
                if (searchResultBoard.getBCategory().equals(board.getBCategory())) {
                }
            }
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "boardList";
    }

    //게시물 페이지
    @GetMapping("/board/view")  // localhost:8090/board/view?id=1
    @Transactional
    public String boardView(Principal principal, Model model, Integer id) {
        String nickname = principal.getName();
        Member member = userRepository.findByEmail(nickname);
        model.addAttribute("member", member);
        model.addAttribute("board", boardService.boardView(id));
        return "boardView";
    }

    //게시글 삭제
    @GetMapping("/board/delete")
    public String boardDelete(Integer id) {
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    //게시글 수정 페이지
    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Principal principal, Model model) {
        String nickname = principal.getName();
        Member member = userRepository.findByEmail(nickname);
        model.addAttribute("member", member);
        model.addAttribute("board", boardService.boardView(id));
        return "boardModify";
    }

    //게시글 수정
    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board, MultipartFile file) throws Exception{
        Board boardTemp = boardService.boardView(id);
        boardTemp.setBTitle(board.getBTitle());
        boardTemp.setBCategory(board.getBCategory());
        boardTemp.setBContent(board.getBContent());
        boardTemp.setBPrice(board.getBPrice());
        boardTemp.setBAddress(board.getBAddress());
        boardTemp.setBDetailAddress(board.getBDetailAddress());
        boardTemp.setFileName(board.getFileName());
        boardTemp.setFilePath(board.getFilePath());

        boardService.write(boardTemp, file);

        return "redirect:/board/list";
    }

}