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
public class OrderCart {
    private String id;
    private List<OrderProduct> products;
    private BigDecimal totalPrice;
}
