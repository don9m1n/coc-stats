package com.dmk.cocstats.domain.article.service;

import com.dmk.cocstats.domain.article.controller.dto.ArticleRequest;
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


    @Transactional(readOnly = true)
    public Page<ArticleResponse> articles(Pageable pageable) {
        return articleRepository.findAll(pageable).map(ArticleResponse::fromArticle);
    }

    @Transactional(readOnly = true)
    public ArticleResponse article(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow();
        return ArticleResponse.fromArticle(article);
    }

    public ArticleResponse saveArticle(Long memberId, ArticleRequest articleRequest) {
        Member member = memberRepository.findById(memberId).orElseThrow();

        Article article = articleRepository.save(ArticleRequest.of(articleRequest, member));
        return ArticleResponse.fromArticle(article);
    }


    public void updateArticle(Long articleId, ArticleRequest articleRequest) {
        Article article = articleRepository.findById(articleId).orElseThrow();

        if (articleRequest.getTitle() != null) {
            article.setTitle(articleRequest.getTitle());
        }
        if (articleRequest.getContent() != null) {
            article.setContent(articleRequest.getContent());
        }

        articleRepository.save(article);
    }

    public void deleteArticle(Long articleId, Long memberId) {
        articleRepository.deleteByIdAndMember_Id(articleId, memberId);
    }
}
