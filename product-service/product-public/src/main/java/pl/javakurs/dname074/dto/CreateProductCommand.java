package pl.javakurs.dname074.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pl.javakurs.dname074.model.ProductType;

import java.math.BigDecimal;

public record CreateProductCommand(
        @NotBlank
        @Size(min = 12, max = 12)
        String sku,
        @NotBlank
        @Size(min = 3, max = 64)
        String name,
        @NotNull
        BigDecimal basePrice,
        @NotNull
        ProductType type,
        @NotBlank
        @Size(min = 3, max = 128)
        String label
) {
}
