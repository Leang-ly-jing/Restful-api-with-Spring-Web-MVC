package co.istad.spring_rest_api.exception;

import lombok.Builder;

@Builder
public record ErrorResponse<T>(
       Boolean status,
        Integer code,
        String message,
       T errors
) {
}
