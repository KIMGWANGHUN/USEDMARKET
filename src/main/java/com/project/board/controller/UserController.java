package com.project.board.controller;

import com.project.board.dto.UserDto;
import com.project.board.entity.Board;
import com.project.board.entity.Member;
import com.project.board.repository.UserRepository;
import com.project.board.sevice.BoardService;
import com.project.board.sevice.RegisterMail;
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
    private final RegisterMail registerMail;

    private String code;
    //메인페이지
//    public String index() {
//        return "index";
//    }

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

    //이메일 인증
    @PostMapping("join/mailConfirm")
    @ResponseBody
    String mailConfirm(@RequestParam("email") String email) throws Exception {
        code = registerMail.sendSimpleMessage(email);
        System.out.println("code" + code);
        return code;
    }

    //인증 확인
    @PostMapping("/join/verifyCode")
    @ResponseBody
    public String verifyCode(@RequestParam("enteredCode") String enteredCode) {
        if (enteredCode.equals(code)) {
            return "success";
        } else {
            return "error";
        }
    }

    //마이페이지
    @GetMapping("/mypageView")
    public String mypage(Principal principal, Model model) {
        String email = principal.getName();
        Member member = userRepository.findByEmail(email);
        model.addAttribute("member",member);
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

    //이메일 찾기 페이지
    @GetMapping("/findEmailPage")
    public String findEmailPage() {
        return "findEmail";
    }

    //이메일 찾기
    @PostMapping(value="findEmail")
    public String findEmail(@RequestParam("name") String name, @RequestParam("phone") String phone,Model model) {
            Member member = userService.findEmail(name, phone);
            model.addAttribute("email","가입된 이메일은"+ maskEmail(member.getEmail())+"입니다");
            model.addAttribute("index","/");
        return "findEmailMsg";
    }

    //찾은 이메일 일부 *표시로 보여주기
    private String maskEmail(String email) {

        int length = email.length();
        int visibleCharacters = Math.min(length, 3);  // 마지막 3글자만 가리기
        String maskedSuffix = "*".repeat(visibleCharacters);
        return email.substring(0, length - 3) + maskedSuffix;
        }
        
    //비밀번호찾기 페이지
    @GetMapping("/memberFindPw")
    public String memberFindPw() {
        return "memberFindPw";
    }



}