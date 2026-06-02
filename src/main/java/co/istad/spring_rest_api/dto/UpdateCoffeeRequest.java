package co.istad.spring_rest_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdateCoffeeRequest(
        @NotBlank(message = "Name is required")
        @Size(min = 3,max = 255)
        String name,
        @NotBlank(message = "Description is required")
        @Size(min = 3,max = 255)
        String description,
        @NotNull(message = "Price is required")
        @Positive(message = "Price must be positive")
        Double price
) {
}
