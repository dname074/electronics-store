package pl.javakurs.dname074.invoice.dto;

import java.time.Instant;

public record InvoiceDto(
        Long id,
        Long externalProviderId,
        Long orderId,
        String invoiceNumber,
        String status,
        Instant createdAt,
        String pdfDownloadUrl
) {
}
