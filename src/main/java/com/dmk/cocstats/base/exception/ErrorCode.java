package com.dmk.cocstats.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    NOT_FOUND_MEMBER("해당 회원이 존재하지 않습니다!",HttpStatus.NOT_FOUND),
    NOT_FOUND_ARTICLE("해당 게시글이 존재하지 않습니다!",HttpStatus.NOT_FOUND),

    ;
    private final String message;
    private final HttpStatus status;

}
