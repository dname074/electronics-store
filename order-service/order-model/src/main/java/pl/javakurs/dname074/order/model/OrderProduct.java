package pl.javakurs.dname074.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderProduct {
    private Long id;
    private String sku;
    private String name;
    private BigDecimal totalPrice;
    private ProductType type;
    private String label;
    private List<Configuration> configurationSnapshot;
}
