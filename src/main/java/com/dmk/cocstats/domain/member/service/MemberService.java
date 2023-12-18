package com.dmk.cocstats.domain.member.service;

import com.dmk.cocstats.domain.member.controller.dto.MemberJoinForm;
import com.dmk.cocstats.domain.member.model.Member;
import com.dmk.cocstats.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member join(MemberJoinForm memberJoinForm) {
        Member member = MemberJoinForm.of(memberJoinForm);
        memberRepository.save(member);

        return member;
    }
}
