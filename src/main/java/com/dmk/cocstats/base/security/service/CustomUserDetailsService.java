package com.dmk.cocstats.base.security.service;

import com.dmk.cocstats.base.security.dto.MemberContext;
import com.dmk.cocstats.domain.member.model.Member;
import com.dmk.cocstats.domain.member.model.MemberRole;
import com.dmk.cocstats.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.debug("일반 로그인 시작: {}", username);

        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("member not found! username: %s".formatted(username)));

        List<GrantedAuthority> authorities = new ArrayList<>();
        if (username.startsWith("admin")) {
            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getRole()));
        } else {
            authorities.add(new SimpleGrantedAuthority(MemberRole.MEMBER.getRole()));
        }

        return new MemberContext(member, authorities);
    }
}
