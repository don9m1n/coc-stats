package com.dmk.cocstats.domain.member.repository;

import com.dmk.cocstats.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
