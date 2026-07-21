package pl.javakurs.dname074.invoice.dto;

import java.math.BigDecimal;

public record InvoiceProductDto(
        String name,
        BigDecimal totalPrice
) {
}
