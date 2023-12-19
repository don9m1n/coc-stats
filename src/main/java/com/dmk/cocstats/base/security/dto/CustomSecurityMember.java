package com.dmk.cocstats.base.security.dto;

import com.dmk.cocstats.domain.member.model.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
public class CustomSecurityMember extends User implements OAuth2User {

    private final Long id;
    private final String nickname;
    private final String email;
    private final String profileImg;

    private Map<String, Object> attributes;
    private String userNameAttributeName;

    public CustomSecurityMember(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getUsername(), member.getPassword(), authorities);
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.profileImg = member.getProfileImg();
    }

    public CustomSecurityMember(Member member, List<GrantedAuthority> authorities, Map<String, Object> attributes, String userNameAttributeName) {
        this(member, authorities);
        this.attributes = attributes;
        this.userNameAttributeName = userNameAttributeName;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return super.getAuthorities();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public String getName() {
        return this.getAttribute(this.userNameAttributeName);
    }
}
