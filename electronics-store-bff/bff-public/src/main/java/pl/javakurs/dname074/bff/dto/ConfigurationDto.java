package pl.javakurs.dname074.bff.dto;

import pl.javakurs.dname074.bff.model.ConfigType;

import java.math.BigDecimal;

public record ConfigurationDto(
        String name,
        ConfigType type,
        BigDecimal price,
        String label,
        Boolean isDefault
) {
}
