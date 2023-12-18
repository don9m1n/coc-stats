package com.dmk.cocstats.domain.member.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String nickname;

    @Column(unique = true)
    private String email;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole = MemberRole.MEMBER;

    private String profileImg;
}
