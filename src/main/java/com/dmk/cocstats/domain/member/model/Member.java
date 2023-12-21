package com.dmk.cocstats.domain.member.model;

import com.dmk.cocstats.base.auditing.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SQLDelete(sql = "UPDATE member SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String nickname;

    @Column(unique = true)
    private String email;

    private boolean emailAuth;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole = MemberRole.MEMBER;

    private String profileImg;

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void successEmailAuth() {
        this.emailAuth = true;
    }

    public static Member of(String username, String password, String nickname, String email, String profileImg) {
        return Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .email(email)
                .emailAuth(true)
                .profileImg(profileImg)
                .build();
    }

}
