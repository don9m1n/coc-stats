package com.dmk.cocstats.domain.member.service;

import com.dmk.cocstats.domain.member.controller.dto.MemberJoinForm;
import com.dmk.cocstats.domain.member.model.Member;
import com.dmk.cocstats.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
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
}
