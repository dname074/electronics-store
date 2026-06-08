package pl.javakurs.dname074.order.dto;

import pl.javakurs.dname074.order.model.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderDto(
        Long id,
        OrderStatus status,
        BigDecimal totalPrice,
        List<OrderProductDto> products,
        Instant createdAt,
        Instant updatedAt
) {
}
