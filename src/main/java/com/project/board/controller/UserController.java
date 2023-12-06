package com.project.board.controller;

import com.project.board.dto.UserDto;
import com.project.board.entity.Board;
import com.project.board.entity.Member;
import com.project.board.repository.UserRepository;
import com.project.board.sevice.BoardService;
import com.project.board.sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    //생성자 주입
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final BoardService boardService;

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
    @GetMapping("/mypageView")
    public String mypage(Principal principal, ModelMap modelMap) {
        String email = principal.getName();
        Member member = userRepository.findByEmail(email);
        modelMap.addAttribute("member",member);
        return "mypageView";
    }

    //마이페이지 수정페이지
    @GetMapping("/modifyMyInfo/{email}")
    public String modifyMyInfo(@PathVariable("email") String email,Model model) {
        model.addAttribute("member",userService.myInfoView(email));
        return "/modifyMyInfo";
    }

    //마이페이지 수정
    @PostMapping("/modify/{email}")
    public String modify(@Valid UserDto uDto, Model model,String email) {
        Member member = Member.updateMember(uDto,passwordEncoder);
        userService.update(member,email);
        return "redirect:/mypageView/";
    }

    //판매글 페이지로 이동
    @GetMapping("/boardList")
    public String boardList(Model model, @PageableDefault(page = 0, size = 5, sort = "bId", direction = Sort.Direction.DESC) Pageable pageable) {
        Board exBoard = new Board("testTitle", "팝니다", "testContetn", "50,000", "서울시", "2023-12-01", 11);
        boardService.write(exBoard);

        Page<Board> list = boardService.boardList(pageable);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "boardList";
    }
}