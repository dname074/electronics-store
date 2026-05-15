package pl.javakurs.dname074.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class Configuration {
    private final ConfigType type;
    private BigDecimal price;
    private Long productId;
    private String label;
}
