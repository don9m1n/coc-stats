package com.dmk.cocstats.domain.member.service;

import com.dmk.cocstats.domain.member.controller.dto.MemberJoinForm;
import com.dmk.cocstats.domain.member.controller.dto.UpdatePasswordForm;
import com.dmk.cocstats.domain.member.model.Member;
import com.dmk.cocstats.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member join(MemberJoinForm memberJoinForm) {
        String password = passwordEncoder.encode(memberJoinForm.getPassword());
        Member member = MemberJoinForm.of(memberJoinForm, password);
        memberRepository.save(member);

        return member;
    }

    public void updatePassword(Long memberId, UpdatePasswordForm updatePasswordForm) {
        Member member = getMemberById(memberId);

        if (!passwordEncoder.matches(updatePasswordForm.getCurrentPassword(), member.getPassword())) {
            throw new RuntimeException("현재 비밀번호가 틀립니다.");
        }

        if (!updatePasswordForm.getNewPassword().equals(updatePasswordForm.getNewPasswordConfirm())) {
            throw new RuntimeException("새 비밀번호를 다시 입력해주세요.");
        }

        String newPassword = passwordEncoder.encode(updatePasswordForm.getNewPassword());
        member.changePassword(newPassword);
    }

    public void delete(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    private Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow();
    }
}
