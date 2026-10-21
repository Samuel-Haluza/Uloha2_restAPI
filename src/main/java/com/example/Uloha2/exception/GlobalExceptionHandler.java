package com.example.Uloha2.exception;

import com.example.Uloha2.response.ResponseFactory;
import com.example.Uloha2.response.ResponseWrapper;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final ResponseFactory response;

    public GlobalExceptionHandler(ResponseFactory response) {
        this.response = response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleValidation(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> Map.of(
                        "field", error.getField(),
                        "message", error.getDefaultMessage() == null ? "Neplatná hodnota" : error.getDefaultMessage()
                ))
                .toList();
        return response.error(HttpStatus.BAD_REQUEST, "Validačné chyby", errors);
    }




    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleConstraintViolation(ConstraintViolationException ex) {
        List<Map<String, String>> errors = ex.getConstraintViolations().stream()
                .map(error -> Map.of(
                        "field", error.getPropertyPath().toString(),
                        "message", error.getMessage()
                ))
                .toList();
        return response.error(HttpStatus.BAD_REQUEST, "Validačné chyby", errors);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return response.error(HttpStatus.BAD_REQUEST,
                "Parameter '" + ex.getName() + "' má neplatnú hodnotu");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleUnreadableMessage() {
        return response.error(HttpStatus.BAD_REQUEST, "Požiadavka obsahuje neplatné údaje");
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleObjectNotFound(ObjectNotFoundException ex) {
        return response.error(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleEmailExists(EmailAlreadyExistsException ex) {
        return response.error(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleDataIntegrityViolation() {
        return response.error(HttpStatus.CONFLICT, "Údaje porušujú databázové obmedzenie");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper<Void>> handleGeneral() {
        return response.error(HttpStatus.INTERNAL_SERVER_ERROR, "Nastala neočakávaná chyba");
    }
}
