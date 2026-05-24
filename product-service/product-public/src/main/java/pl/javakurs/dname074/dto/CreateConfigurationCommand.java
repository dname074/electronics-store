package pl.javakurs.dname074.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pl.javakurs.dname074.model.ConfigType;

import java.math.BigDecimal;

public record CreateConfigurationCommand(
        @NotBlank
        @Size(min = 3, max = 64)
        String name,
        @NotNull
        ConfigType type,
        @NotNull
        BigDecimal price,
        @NotBlank
        @Size(min = 3, max = 128)
        String label,
        Long productId
) {
}
