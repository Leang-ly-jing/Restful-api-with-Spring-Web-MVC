package co.istad.spring_rest_api.exception;

import co.istad.spring_rest_api.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@Slf4j
@RestControllerAdvice
public class AppException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e){

        log.error("Validation Exception happened");

        List<ErrorResponse> errorResponses = new ArrayList<>();
        e.getFieldErrors().forEach(fieldError -> errorResponses.add (new ErrorResponse(fieldError.getField(),fieldError.getDefaultMessage())));

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Validation is Errored");
        response.put("status", false);
        response.put("code", e.getStatusCode().value());
        response.put("errors" , errorResponses);
        return ResponseEntity.badRequest().body(response);
    }
}

