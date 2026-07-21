package pl.javakurs.dname074.invoice.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderDto(
        Long id,
        BigDecimal totalPrice,
        List<InvoiceProductDto> products,
        CustomerDto customer,
        Instant createdAt,
        Instant updatedAt
) {
}
