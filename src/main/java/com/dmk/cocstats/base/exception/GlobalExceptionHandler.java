package com.dmk.cocstats.base.exception;

import com.dmk.cocstats.base.exception.custom.CustomException;
import com.dmk.cocstats.base.exception.custom.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public String handle404(NotFoundException e, Model model) {
        ErrorCode errorCode = e.getErrorCode();
        log.debug("errorCode: {}", errorCode);
        model.addAttribute("errorCode", e.getErrorCode());
        model.addAttribute("message", e.getMessage());
        return "error/404";
    }
}
