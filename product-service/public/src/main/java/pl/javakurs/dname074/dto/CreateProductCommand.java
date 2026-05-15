package pl.javakurs.dname074.dto;

import pl.javakurs.dname074.model.ProductType;

import java.math.BigDecimal;

public record CreateProductCommand(
        String name,
        BigDecimal basePrice,
        ProductType type,
        String label
) {
}
