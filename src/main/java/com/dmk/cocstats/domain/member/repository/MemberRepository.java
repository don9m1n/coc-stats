package com.dmk.cocstats.domain.member.repository;

import com.dmk.cocstats.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

    // 이메일 인증이 완료된 회원 검색
    Optional<Member> findByUsernameAndEmailAuthIsTrue(String username);
    Optional<Member> findByEmail(String email);
}
