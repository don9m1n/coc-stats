package com.dmk.cocstats.domain.member.controller;

import com.dmk.cocstats.domain.member.controller.dto.MemberJoinForm;
import com.dmk.cocstats.domain.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String join(@ModelAttribute MemberJoinForm memberJoinForm) {
        return "members/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute MemberJoinForm memberJoinForm, Model model) {
        model.addAttribute("member", memberService.join(memberJoinForm));
        return "redirect:/members/login";
    }

    // 시큐리티를 사용하면 로그인 기능은 자동으로 처리해줌.
    // loadByUser()
    @GetMapping("/login")
    public String login() {
        return "members/login";
    }

}
