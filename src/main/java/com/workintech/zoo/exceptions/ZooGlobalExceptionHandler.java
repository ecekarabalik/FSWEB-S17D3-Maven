package com.workintech.zoo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ZooGlobalExceptionHandler {

    @ExceptionHandler(ZooException.class)
    public ResponseEntity<ZooErrorResponse> handleZooException(ZooException ex) {
        log.error("ZooException: {}", ex.getMessage());
        ZooErrorResponse body = new ZooErrorResponse(
                ex.getMessage(),
                ex.getHttpStatus().value(),
                System.currentTimeMillis()
        );
        return ResponseEntity.status(ex.getHttpStatus()).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ZooErrorResponse> handleGenericException(Exception ex) {
        log.error("Unexpected error", ex);
        ZooErrorResponse body = new ZooErrorResponse(
                "Unexpected error",
                500,
                System.currentTimeMillis()
        );
        return ResponseEntity.status(500).body(body);
    }
}
