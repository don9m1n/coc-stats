package com.dmk.cocstats.domain.member.controller.dto;

import com.dmk.cocstats.domain.member.model.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberJoinForm {

    private String username;

    private String password;

    private String passwordConfirm;

    private String nickname;

    private String email;

    public static Member of(MemberJoinForm memberJoinForm) {
        return Member.builder()
                .username(memberJoinForm.getUsername())
                .password(memberJoinForm.getPassword())
                .nickname(memberJoinForm.getNickname())
                .email(memberJoinForm.getEmail())
                .build();
    }

}
