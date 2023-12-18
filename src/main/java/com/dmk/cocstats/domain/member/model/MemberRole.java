package com.dmk.cocstats.domain.member.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberRole {

    MEMBER("ROLE_MEMBER"),
    ADMIN("ROLE_ADMIN");

    private final String role;
}
