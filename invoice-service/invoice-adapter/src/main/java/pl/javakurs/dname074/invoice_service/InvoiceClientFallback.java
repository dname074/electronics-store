package pl.javakurs.dname074.invoice_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.javakurs.dname074.invoice.dto.InvoiceApiResponse;
import pl.javakurs.dname074.invoice.dto.InvoiceRequest;

@Component
@Slf4j
public class InvoiceClientFallback implements InvoiceClient {
    @Override
    public InvoiceApiResponse generateInvoice(InvoiceRequest invoice) {
        log.info("Fallback occurred after requesting invoice api");
        return new InvoiceApiResponse(null, null, null);
    }
}
