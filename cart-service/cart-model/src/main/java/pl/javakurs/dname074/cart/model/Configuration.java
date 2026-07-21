package pl.javakurs.dname074.cart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Configuration {
    private Long id;
    private String name;
    private ConfigType type;
    private BigDecimal price;
    private String label;
    private Boolean isDefault;
}
