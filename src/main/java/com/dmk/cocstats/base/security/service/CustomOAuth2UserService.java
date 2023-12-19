package com.dmk.cocstats.base.security.service;

import com.dmk.cocstats.base.security.dto.CustomSecurityMember;
import com.dmk.cocstats.domain.member.model.Member;
import com.dmk.cocstats.domain.member.model.MemberRole;
import com.dmk.cocstats.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.debug("소셜 로그인 시작!: {}", userRequest);
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuth2Attribute oAuth2Attribute = OAuth2Attribute.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        Map<String, Object> memberAttribute = oAuth2Attribute.convertToMap();

        log.debug("memberAttribute: {}", memberAttribute);

        String email = (String) memberAttribute.get("email");
        String provider = (String) memberAttribute.get("provider");
        String username = "%s_%s".formatted(provider, email.split("@")[0]);

        Member member = null;

        if (isNew(username)) {
            String name = provider + "_" + memberAttribute.get("name");
            String picture = (String) memberAttribute.get("picture");

            member = Member.of(username, "", name, provider + "_" + email, picture);
            memberRepository.save(member);
        } else {
            member = memberRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("member not found!"));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(MemberRole.MEMBER.getRole()));

        return new CustomSecurityMember(member, authorities, memberAttribute, userNameAttributeName);
    }

    private boolean isNew(String username) {
        return memberRepository.findByUsername(username).isEmpty();
    }
}
