package pl.javakurs.dname074.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    private Long id;
    private OrderStatus status;
    private BigDecimal totalPrice;
    private List<OrderProduct> products;
    private Instant createdAt;
    private Instant updatedAt;
}
