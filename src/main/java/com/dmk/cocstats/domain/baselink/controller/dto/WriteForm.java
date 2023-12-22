package com.dmk.cocstats.domain.baselink.controller.dto;

import com.dmk.cocstats.domain.baselink.model.BaseLinkArticle;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WriteForm {

    private String title;
    private String content;
    private String link;

    public static BaseLinkArticle of(WriteForm writeForm) {
        return BaseLinkArticle.builder()
                .title(writeForm.getTitle())
                .content(writeForm.getContent())
                .baseLink(writeForm.getLink())
                .build();
    }
}
