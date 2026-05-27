package pl.javakurs.dname074.cart.dto;

import pl.javakurs.dname074.cart.model.ConfigType;

import java.math.BigDecimal;

public record ConfigurationDto(
        Long id,
        String name,
        ConfigType type,
        BigDecimal price,
        String label,
        Boolean isDefault
) {
}
