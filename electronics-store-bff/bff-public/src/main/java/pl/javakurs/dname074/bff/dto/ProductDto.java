package pl.javakurs.dname074.bff.dto;

import pl.javakurs.dname074.bff.model.ProductType;

import java.math.BigDecimal;
import java.util.List;

public record ProductDto(
        String sku,
        String name,
        BigDecimal totalPrice,
        ProductType type,
        String label,
        List<ConfigurationDto> configurations
) {
}
