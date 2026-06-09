package pl.javakurs.dname074.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@AllArgsConstructor
@NoArgsConstructor
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
