package com.syscho.grpc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> error(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(
                ErrorResponse.builder()
                        .msg(exception.getMessage())
                        .status(HttpStatus.BAD_REQUEST.name())
                        .createdTime(LocalDateTime.now()).build()

        );
    }
}
