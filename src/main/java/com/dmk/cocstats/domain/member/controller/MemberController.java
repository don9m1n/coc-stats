package com.dmk.cocstats.domain.member.controller;

import com.dmk.cocstats.domain.member.controller.dto.MemberJoinForm;
import com.dmk.cocstats.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/login")
    public String login() {
        return "members/login";
    }

}
