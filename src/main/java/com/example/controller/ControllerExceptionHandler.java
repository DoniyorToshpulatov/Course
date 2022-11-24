package com.example.controller;

import com.example.exception.CreationException;
import com.example.exception.ItemNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.cert.CertificateRevokedException;

@ControllerAdvice
public class    ControllerExceptionHandler {


    @ExceptionHandler({CreationException.class, ItemNotFoundException.class})
    public ResponseEntity<?> handle(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }


}

