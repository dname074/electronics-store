package pl.javakurs.dname074.bff.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String sku;
    private String name;
    private BigDecimal totalPrice;
    private ProductType type;
    private String label;
    private List<Configuration> configurations;
}
