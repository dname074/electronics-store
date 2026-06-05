package pl.javakurs.dname074.order.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long id;
    private OrderStatus status;
    private BigDecimal totalPrice;
    private List<OrderProduct> products;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
