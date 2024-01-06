package com.dmk.cocstats.base;

import com.dmk.cocstats.domain.article.controller.dto.ArticleRequest;
import com.dmk.cocstats.domain.article.service.ArticleService;
import com.dmk.cocstats.domain.baselink.controller.dto.WriteForm;
import com.dmk.cocstats.domain.baselink.service.BaseLinkArticleService;
import com.dmk.cocstats.domain.member.model.Member;
import com.dmk.cocstats.domain.member.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev")
public class InitDevData {

    @Bean
    CommandLineRunner init(ArticleService articleService, BaseLinkArticleService baseLinkArticleService, MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Member member1 = Member.of("donmin", passwordEncoder.encode("1234"), "Keria", "dongmin@email.com", null);
            memberRepository.save(member1);

            Member member2 = Member.of("sana", passwordEncoder.encode("1234"), "snan", "sana@email.com", null);
            memberRepository.save(member2);

            for (int i = 1; i <= 10; i++) {
                baseLinkArticleService.saveArticle(create("title" + i, "content" + i));
            }

            for (int i = 1; i <= 22; i++) {
                ArticleRequest form = ArticleRequest.builder()
                        .title("제목" + i)
                        .content("내용" + i)
                        .build();
                articleService.write(member1.getId(), form);
            }
        };
    }

    private WriteForm create(String title, String content) {
        return WriteForm.builder()
                .title(title)
                .content(content)
                .link("https://link.clashofclans.com/en/?action=OpenLayout&id=TH15%3AHV%3AAAAAHgAAAAJHA_9Oaskut4uBREguvfNc")
                .build();
    }
}
