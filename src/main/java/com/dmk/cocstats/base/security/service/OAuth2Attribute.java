package com.dmk.cocstats.base.security.service;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
@Builder(access = AccessLevel.PRIVATE)
@SuppressWarnings("unchecked")
public class OAuth2Attribute {

    private Map<String, Object> attributes; // 소셜 로그인 사용자의 속성 정보를 담는 Map
    private String attributeKey; // 사용자 속성의 키 값
    private String email; // 이메일
    private String name; // 이름
    private String picture; // 프로필 사진
    private String provider; // 플랫폼

    static OAuth2Attribute of(String provider, String attributeKey, Map<String, Object> attributes) {
        // 각 플랫폼 별로 제공해주는 데이터가 조금씩 다르기 때문에 분기 처리함.
        return switch (provider) {
            case "kakao" -> kakao(provider, attributeKey, attributes);
            case "google" -> google(provider, attributeKey, attributes);
            case "naver" -> naver(provider, attributeKey, attributes);
            default -> throw new RuntimeException();
        };
    }

    private static OAuth2Attribute kakao(String provider, String attributeKey, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");

        return OAuth2Attribute.builder()
                .email((String) kakaoAccount.get("email"))
                .name((String) kakaoProfile.get("nickname"))
                .picture((String) kakaoProfile.get("profile_image_url"))
                .provider(provider)
                .attributes(kakaoAccount)
                .attributeKey(attributeKey)
                .build();
    }

    private static OAuth2Attribute google(String provider, String attributeKey, Map<String, Object> attributes) {
        log.debug("google: {}", attributes);
        return OAuth2Attribute.builder()
                .email((String) attributes.get("email"))
                .name((String) attributes.get("name"))
                .picture((String)attributes.get("picture"))
                .provider(provider)
                .attributes(attributes)
                .attributeKey(attributeKey)
                .build();
    }

    private static OAuth2Attribute naver(String provider, String attributeKey, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuth2Attribute.builder()
                .email((String) response.get("email"))
                .name((String) response.get("nickname"))
                .picture((String) response.get("profile_image"))
                .provider(provider)
                .attributes(response)
                .attributeKey(attributeKey)
                .build();
    }


    // OAuth2Attribute -> Map<String, Object>
    public Map<String, Object> convertToMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", attributeKey);
        map.put("key", attributeKey);
        map.put("email", email);
        map.put("name", name);
        map.put("picture", picture);
        map.put("provider", provider);

        return map;
    }

}
