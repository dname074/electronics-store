package pl.javakurs.dname074.dto;

import pl.javakurs.dname074.model.ProductType;

import java.math.BigDecimal;
import java.util.List;

public record ProductDto(
        Long id,
        String sku,
        String name,
        BigDecimal basePrice,
        BigDecimal totalPrice,
        ProductType type,
        String label,
        List<ConfigurationDto> configurations
) {
}
