package pl.javakurs.dname074.cart.dto;

import pl.javakurs.dname074.cart.model.ProductType;

import java.math.BigDecimal;
import java.util.List;

public record CartProductDto(
        Long id,
        String sku,
        String name,
        BigDecimal totalPrice,
        ProductType type,
        String label,
        List<ConfigurationDto> configurations
) {
}
