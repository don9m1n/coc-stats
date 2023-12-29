package com.dmk.cocstats.domain.article.service;

import com.dmk.cocstats.domain.article.controller.dto.ArticleForm;
import com.dmk.cocstats.domain.article.controller.dto.ArticleResponse;
import com.dmk.cocstats.domain.article.model.Article;
import com.dmk.cocstats.domain.article.repository.ArticleRepository;
import com.dmk.cocstats.domain.member.model.Member;
import com.dmk.cocstats.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    public Page<ArticleResponse> articles(Pageable pageable) {
        return articleRepository.findAll(pageable).map(ArticleResponse::fromArticle);
    }

    public ArticleResponse article(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow();
        return ArticleResponse.fromArticle(article);
    }

    public ArticleResponse write(Long memberId, ArticleForm articleForm) {
        Member member = memberRepository.findById(memberId).orElseThrow();

        Article article = articleRepository.save(ArticleForm.of(articleForm, member));
        return ArticleResponse.fromArticle(article);
    }



}
