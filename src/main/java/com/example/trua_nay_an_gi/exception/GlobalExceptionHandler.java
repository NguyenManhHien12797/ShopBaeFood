package com.example.trua_nay_an_gi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler({ MerchantNotFoundException.class, AccountNotFoundException.class })
    public ResponseEntity<String> handleExceptionA(Exception e) {
        logger.error(String.valueOf(e));
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnwantedException(Exception e) {
        logger.error(String.valueOf(e));
        return ResponseEntity.status(500).body("Unknow error");
    }
}