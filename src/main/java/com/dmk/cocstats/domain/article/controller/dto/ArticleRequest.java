package com.dmk.cocstats.domain.article.controller.dto;

import com.dmk.cocstats.domain.article.model.Article;
import com.dmk.cocstats.domain.member.model.Member;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequest {

    private String title;
    private String content;

    public static Article of(ArticleRequest articleRequest, Member member) {
        return Article.builder()
                .title(articleRequest.getTitle())
                .content(articleRequest.getContent())
                .member(member)
                .build();
    }

}
