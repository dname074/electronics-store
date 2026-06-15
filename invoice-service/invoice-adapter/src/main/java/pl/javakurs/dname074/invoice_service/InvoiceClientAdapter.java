package pl.javakurs.dname074.invoice_service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.javakurs.dname074.invoice.domain.InvoiceClientProvider;
import pl.javakurs.dname074.invoice.dto.InvoiceRequest;
import pl.javakurs.dname074.invoice.model.CreateInvoiceCommand;
import pl.javakurs.dname074.invoice.model.Invoice;

@RequiredArgsConstructor
@Component
public class InvoiceClientAdapter implements InvoiceClientProvider {
    private final InvoiceClient invoiceClient;
    private final InvoiceMapper invoiceMapper;

    @Value("${fakturownia.api.token}")
    private String apiToken;

    @Override
    public Invoice generateInvoice(CreateInvoiceCommand createInvoiceCommand) {
        return invoiceMapper.responseToPojo(
                invoiceClient.generateInvoice(
                        new InvoiceRequest(apiToken, createInvoiceCommand)
                )
        );
    }
}
