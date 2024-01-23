package com.example.upgrade.controller;

import com.example.upgrade.dto.MemberSaveForm;
import com.example.upgrade.dto.MemberUpdateForm;
import com.example.upgrade.entity.Member;
import com.example.upgrade.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/add")
    public String addPage(@ModelAttribute("member")Member member){

        return "members/addMemberForm";
    }

    @PostMapping("/members/add")
    public String save(@Validated @ModelAttribute("member")MemberSaveForm dto, BindingResult bindingResult, RedirectAttributes redirect){
        if(bindingResult.hasErrors()){
            return "members/addMemberForm";
        }

        memberService.save(dto);
        redirect.addFlashAttribute("msg", "회원가입이 완료되었습니다.");

        return "redirect:/";
    }

    @GetMapping("/members/{memberId}/myPage")
    public String myPage(@PathVariable("memberId") int memberId, Model model){
        Member member = memberService.getMember(memberId);

        model.addAttribute("member", member);

        return "members/myPage";
    }

    @GetMapping("/members/{memberId}/edit")
    public String editPage(@PathVariable("memberId")int memberId, Model model){
        model.addAttribute("member", memberService.getMember(memberId));

        return "members/editMemberForm";
    }

    @PostMapping("/members/update")
    public String update(@Validated @ModelAttribute("member")MemberUpdateForm dto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "members/editMemberForm";
        }

        memberService.memberUpdate(dto);

        return "redirect:/members/" + dto.getMemberId() + "/myPage";
    }

    @GetMapping("/members/{memberId}/delete")
    public String memberDelete(@PathVariable("memberId") int memberId){
        memberService.memberDelete(memberId);

        return "redirect:/members/logout";
    }
}
