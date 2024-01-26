package com.example.upgrade.controller;

import com.example.upgrade.SessionConst;
import com.example.upgrade.dto.LoginForm;
import com.example.upgrade.entity.Member;
import com.example.upgrade.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/members/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm dto){

        return "members/loginForm";
    }

    @PostMapping("/members/login")
    public String login(@Validated @ModelAttribute LoginForm dto, BindingResult bindingResult, Model model,
                        @RequestParam(name = "redirectURL", defaultValue = "/boards") String redirectURL, HttpServletRequest request){

        if(bindingResult.hasErrors()){
            return "members/loginForm";
        }

        // 로그인
        Member loginMember = loginService.login(dto.getId(), dto.getPassword());
        
        if(loginMember == null){
            bindingResult.reject("msg", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "members/loginForm";
        }

        // 로그인 성공 TODO
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:" + redirectURL;
    }

    @RequestMapping("/members/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }

        return "redirect:/";
    }
}
