package com.sprint.be_java_hisp_w23_g04.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ControllerException {
    /*
    @ExceptionHandler(LinkInvalidPasswordException.class)
    public ResponseEntity<?> invalidPassword(LinkInvalidPasswordException e) {
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.FORBIDDEN);
    }
    */

}
