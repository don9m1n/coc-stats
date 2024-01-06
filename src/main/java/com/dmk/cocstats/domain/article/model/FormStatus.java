package com.dmk.cocstats.domain.article.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormStatus {
    CREATE("저장", false),
    UPDATE("수정", true);

    private final String description;
    private final Boolean update;

    }
