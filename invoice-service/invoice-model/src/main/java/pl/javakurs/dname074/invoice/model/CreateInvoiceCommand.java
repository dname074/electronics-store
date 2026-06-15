package pl.javakurs.dname074.invoice.model;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
public record CreateInvoiceCommand(
        Long id,
        String kind,
        String number,
        LocalDate sellDate,
        LocalDate issueDate,
        LocalDate paymentTo,
        String sellerName,
        String buyerName,
        String buyerCountry,
        String buyerCity,
        String buyerPostCode,
        String buyerStreet,
        BigDecimal priceNet,
        BigDecimal priceGross,
        String currency,
        List<InvoiceProduct> positions
) {
}
