package com.dmk.cocstats.domain.article.controller.dto;

import com.dmk.cocstats.domain.article.model.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {

    private Long id;
    private String title;
    private String content;
    private String nickname; // 작성자
    private LocalDateTime createdAt;

    public static ArticleResponse fromArticle(Article article) {
        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .nickname(article.getMember().getNickname())
                .createdAt(article.getCreatedAt())
                .build();
    }

}
