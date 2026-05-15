package pl.javakurs.dname074.dto;

import pl.javakurs.dname074.model.ConfigType;

import java.math.BigDecimal;

public record ConfigurationDto(
        Long id,
        ConfigType type,
        BigDecimal price,
        Boolean isDefault,
        String label
) {
}
