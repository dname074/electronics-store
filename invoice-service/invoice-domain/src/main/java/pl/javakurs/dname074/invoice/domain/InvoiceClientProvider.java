package pl.javakurs.dname074.invoice.domain;

import pl.javakurs.dname074.invoice.model.CreateInvoiceCommand;
import pl.javakurs.dname074.invoice.model.Invoice;

public interface InvoiceClientProvider {
    Invoice generateInvoice(CreateInvoiceCommand createInvoiceCommand);
}
