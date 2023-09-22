package ru.sber.SberCoffee.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;

/**
 * The type Error handler.
 */
@Slf4j
@RestControllerAdvice("ru.sber.SberCoffee.controller")
public class ErrorHandler {

    /**
     * Handle method argument type mismatch response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = "Invalid parameter type: " + ex.getName();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    /**
     * Invalid path variable exception map.
     *
     * @param e the e
     * @return the map
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> invalidPathVariableException(final Exception e) {
        log.warn("Validation error:  {}. Stacktrace {}", e.getMessage(), e.getCause());
        return Map.of("Error", e.getMessage());
    }
}
