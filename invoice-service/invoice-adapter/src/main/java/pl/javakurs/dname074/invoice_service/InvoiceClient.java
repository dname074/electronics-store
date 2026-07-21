package pl.javakurs.dname074.invoice_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.javakurs.dname074.invoice.dto.InvoiceApiResponse;
import pl.javakurs.dname074.invoice.dto.InvoiceRequest;

@FeignClient(
        name = "invoiceClient",
        configuration = InvoiceClientConfiguration.class,
        fallback = InvoiceClientFallback.class
)
interface InvoiceClient {
    @PostMapping("/invoices.json")
    InvoiceApiResponse generateInvoice(@RequestBody InvoiceRequest invoice);
}
