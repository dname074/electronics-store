package pl.javakurs.dname074.bff.dto;

import java.math.BigDecimal;
import java.util.List;

public record CartDto(
        String id,
        List<ProductDto> products,
        BigDecimal totalPrice
) {
}
