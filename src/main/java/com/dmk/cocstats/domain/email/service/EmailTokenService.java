package com.dmk.cocstats.domain.email.service;

import com.dmk.cocstats.domain.email.model.EmailToken;
import com.dmk.cocstats.domain.email.repository.EmailTokenRepository;
import com.dmk.cocstats.domain.member.model.Member;
import com.dmk.cocstats.domain.member.repository.MemberRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@EnableAsync
@Transactional
@RequiredArgsConstructor
public class EmailTokenService {

    private final EmailTokenRepository emailTokenRepository;
    private final JavaMailSender mailSender;
    private final MemberRepository memberRepository;

    @Async
    public void send(String email) {

        String token = createEmailToken(email);
        String link = "http://localhost:8080/members/auth-email/%s/%s".formatted(email, token);

        MimeMessage message = mailSender.createMimeMessage();

        try {
            message.setSubject("[COC STATS] 회원가입을 축하드립니다!");
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setText(link, "UTF-8", "HTML");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        mailSender.send(message);
    }

    // 인증 메일 검증
    public void authEmailToken(String email, String token) {
        EmailToken emailToken = emailTokenRepository.findByEmailAndAuthTokenAndExpireDateGreaterThanAndDeletedAtNull(email, token, LocalDateTime.now())
                .orElseThrow(() -> new RuntimeException("유효한 이메일 인증 토큰이 없습니다."));

        emailToken.useToken();

        // TODO: 회원 이메일 인증 처리
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("member not found!"));
        member.successEmailAuth();
    }

    private String createEmailToken(String email) {
        EmailToken emailToken = EmailToken.builder()
                .email(email)
                .authToken(UUID.randomUUID().toString())
                .expireDate(LocalDateTime.now().plusMinutes(3L))
                .build();

        return emailTokenRepository.save(emailToken).getAuthToken();
    }

}
