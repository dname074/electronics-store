package pl.javakurs.dname074.order.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCustomerCommand(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank
        String country,
        @NotBlank
        String town,
        @NotBlank
        String postalCode,
        @NotBlank
        String street,
        Integer houseNumber
) {
}
