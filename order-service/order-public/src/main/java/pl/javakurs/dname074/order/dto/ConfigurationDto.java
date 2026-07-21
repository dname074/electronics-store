package pl.javakurs.dname074.order.dto;

import pl.javakurs.dname074.order.model.ConfigType;

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
