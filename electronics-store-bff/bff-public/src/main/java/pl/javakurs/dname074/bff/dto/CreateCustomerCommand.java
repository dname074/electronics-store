package pl.javakurs.dname074.bff.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
        @Size(min = 6, max = 6)
        String postalCode,
        @NotBlank
        String street,
        Integer houseNumber
) {
}
