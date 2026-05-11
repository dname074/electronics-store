package pl.javakurs.dname074.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private ElectronicsType type;
    private List<Configuration> configuration;
}
