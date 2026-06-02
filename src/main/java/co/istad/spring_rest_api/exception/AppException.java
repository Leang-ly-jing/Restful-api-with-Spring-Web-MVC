package co.istad.spring_rest_api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Slf4j
@RestControllerAdvice
public class AppException {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleServiceException(ResponseStatusException e){
        ErrorResponse<?> errorResponse= ErrorResponse.builder()
                .status(false)
                .code(e.getStatusCode().value())
                .message("Service Exception error")
                .errors(e.getReason())
                .build();

        return ResponseEntity.status(e.getStatusCode())
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse<?> handleValidationException(MethodArgumentNotValidException e){

        log.error("Validation Exception happened");

        List<FieldErrorResponse> fieldErrorResponsesList = new ArrayList<>();

        e.getFieldErrors()
                .forEach(fieldError -> {
                    FieldErrorResponse fieldErrorResponse = FieldErrorResponse.builder()
                            .field(fieldError.getField())
                            .message(fieldError.getDefaultMessage())
                            .build();

                    fieldErrorResponsesList.add(fieldErrorResponse);
                });

       return ErrorResponse.builder()
               .status(false)
               .code(HttpStatus.BAD_REQUEST.value())
               .message("Validation is Error")
               .errors(fieldErrorResponsesList)
               .build();
    }
}

