package pl.javakurs.dname074.dto;

import pl.javakurs.dname074.model.ConfigType;

import java.math.BigDecimal;

public record CreateConfigurationCommand(
        String name,
        ConfigType type,
        BigDecimal price,
        String label,
        Long productId
) {
}
