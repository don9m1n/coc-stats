package com.dmk.cocstats.domain.member.controller;

import com.dmk.cocstats.base.security.dto.MemberContext;
import com.dmk.cocstats.domain.email.service.EmailTokenService;
import com.dmk.cocstats.domain.member.controller.dto.MemberJoinForm;
import com.dmk.cocstats.domain.member.controller.dto.UpdatePasswordForm;
import com.dmk.cocstats.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final EmailTokenService emailTokenService;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/join")
    public String joinForm(@ModelAttribute MemberJoinForm memberJoinForm) {
        return "members/join";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/join")
    public String join(@ModelAttribute MemberJoinForm memberJoinForm, HttpServletResponse response) {
        memberService.join(memberJoinForm);
        return "redirect:/members/login";
    }

    @GetMapping("/auth-email/{email}/{token}")
    public String authEmailToken(@PathVariable String email, @PathVariable String token) {
        emailTokenService.authEmailToken(email, token);

        return "redirect:/members/login";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String loginForm() {
        return "members/login";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/updatePassword")
    public String updatePasswordForm(@ModelAttribute UpdatePasswordForm updatePasswordForm) {
        return "members/updatePasswordForm";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/updatePassword")
    public String updatePassword(
            @ModelAttribute UpdatePasswordForm updatePasswordForm,
            @AuthenticationPrincipal MemberContext memberContext
    ) {
        memberService.updatePassword(memberContext.getId(), updatePasswordForm);
        return "redirect:/members/logout";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete")
    public String delete(@AuthenticationPrincipal MemberContext memberContext) {
        memberService.delete(memberContext.getId());
        return "redirect:/members/logout";
    }

}
