package pl.javakurs.dname074.order.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderCartDto(
        String id,
        List<OrderProductDto> products,
        BigDecimal totalPrice
) {
}
