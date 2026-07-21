package pl.javakurs.dname074.invoice.dto;

import pl.javakurs.dname074.invoice.model.CreateInvoiceCommand;

public record InvoiceRequest(
        String apiToken,
        CreateInvoiceCommand invoice
) {}
