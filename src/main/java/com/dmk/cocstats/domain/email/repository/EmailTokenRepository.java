package com.dmk.cocstats.domain.email.repository;

import com.dmk.cocstats.domain.email.model.EmailToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EmailTokenRepository extends JpaRepository<EmailToken, Long> {

    Optional<EmailToken> findByEmailAndAuthTokenAndExpireDateGreaterThanAndDeletedAtNull(String email, String token, LocalDateTime now);

}
