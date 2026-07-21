package pl.javakurs.dname074.invoice.dto;

public record InvoiceApiResponse(
        Long id,
        String number,
        String status
) {
}
