package pl.javakurs.dname074.bff.dto;

import pl.javakurs.dname074.bff.model.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderDto(
        OrderStatus status,
        BigDecimal totalPrice,
        List<ProductDto> products,
        CustomerDto customer,
        Instant createdAt,
        Instant updatedAt
) {
}
