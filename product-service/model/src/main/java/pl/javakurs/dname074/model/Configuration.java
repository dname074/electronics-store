package pl.javakurs.dname074.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
class Configuration {
    private final ConfigType configType;
    private String value;
    private BigDecimal price;
    private boolean isDefault;
    private Long productId;
    private String label;
}
