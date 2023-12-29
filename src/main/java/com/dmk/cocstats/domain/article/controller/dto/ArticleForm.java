package com.dmk.cocstats.domain.article.controller.dto;

import com.dmk.cocstats.domain.article.model.Article;
import com.dmk.cocstats.domain.member.model.Member;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleForm {

    private String type; // 공지, 설문, 일반
    private String title;
    private String content;

    public static Article of(ArticleForm articleForm, Member member) {
        return Article.builder()
                .title(articleForm.getTitle())
                .content(articleForm.getContent())
                .member(member)
                .build();
    }

}
