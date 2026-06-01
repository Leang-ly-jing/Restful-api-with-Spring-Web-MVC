package co.istad.spring_rest_api.dto;

public record ErrorResponse(
        String field,
        String message
) {
}
