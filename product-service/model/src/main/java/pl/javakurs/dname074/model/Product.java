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
    private String sku;
    private String name;
    private BigDecimal basePrice;
    private ProductType type;
    private String label;
    private List<ProductConfiguration> configurations;

    public void update(Product product) {
        this.sku = product.getSku();
        this.name = product.getName();
        this.basePrice = product.getBasePrice();
        this.type = product.getType();
        this.label = product.getLabel();
    }

    public void addConfiguration(ProductConfiguration configuration) {
        configurations.add(configuration);
    }
}
