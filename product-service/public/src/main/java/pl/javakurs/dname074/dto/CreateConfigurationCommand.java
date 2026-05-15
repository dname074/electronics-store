package pl.javakurs.dname074.dto;

import pl.javakurs.dname074.model.ConfigType;

import java.math.BigDecimal;

public record CreateConfigurationCommand(
        ConfigType type,
        BigDecimal price,
        Boolean isDefault,
        String label
) {
}
