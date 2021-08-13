package com.thor.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * global exception handler
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public void handleOthers(Exception e) {
        e.printStackTrace();
    }
}
