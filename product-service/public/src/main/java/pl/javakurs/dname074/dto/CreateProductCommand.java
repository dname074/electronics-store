package pl.javakurs.dname074.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import pl.javakurs.dname074.model.ProductType;

import java.math.BigDecimal;

public record CreateProductCommand(
        @Size(min = 12, max = 12)
        @NotBlank
        String sku,
        String name,
        BigDecimal basePrice,
        ProductType type,
        String label
) {
}
