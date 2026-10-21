package com.example.Uloha2.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ResponseFactory {

    public ResponseEntity<ResponseWrapper<Void>> ok(String message) {
        return ResponseEntity.ok(new ResponseWrapper<>(null, message, null));
    }

    public <T> ResponseEntity<ResponseWrapper<T>> ok(String message, T data) {
        return ResponseEntity.ok(new ResponseWrapper<>(data, message, null));
    }

    public <T> ResponseEntity<ResponseWrapper<T>> created(String message, T data) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>(data, message, null));
    }

    public ResponseEntity<ResponseWrapper<Void>> error(HttpStatus status, String message) {
        return ResponseEntity.status(status)
                .body(new ResponseWrapper<>(null, message, null));
    }

    public ResponseEntity<ResponseWrapper<Void>> error(
            HttpStatus status, String message, List<Map<String, String>> errors) {
        return ResponseEntity.status(status)
                .body(new ResponseWrapper<>(null, message, errors));
    }
}
