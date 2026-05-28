package co.istad.spring_rest_api.dto;

public record CoffeeResponse(
        String name,
        String description,
        double price
) {
}
