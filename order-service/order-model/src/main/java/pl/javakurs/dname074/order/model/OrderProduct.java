package pl.javakurs.dname074.order.model;

import java.math.BigDecimal;
import java.util.List;

public class OrderProduct {
    private Long id;
    private String sku;
    private String name;
    private BigDecimal totalPrice;
    private ProductType type;
    private String label;
    private List<Configuration> configurations;
}
