package com.dmk.cocstats.domain.article.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArticleType {
    NOTICE("공지"),
    SURVEY("설문"),
    NORMAL("일반")
    ;

    private final String type;
}
