package com.dmk.cocstats.base.exception.custom;

import com.dmk.cocstats.base.exception.ErrorCode;

public class NotFoundException extends CustomException {
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
