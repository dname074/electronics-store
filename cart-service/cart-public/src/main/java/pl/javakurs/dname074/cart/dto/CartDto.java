package pl.javakurs.dname074.cart.dto;

import java.math.BigDecimal;
import java.util.List;

public record CartDto(
        String id,
        List<CartProductDto> products,
        BigDecimal totalPrice
) {
}