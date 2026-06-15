package pl.javakurs.dname074.invoice.domain;

import pl.javakurs.dname074.invoice.model.Invoice;
import pl.javakurs.dname074.invoice.model.Order;

public interface InvoiceServiceProvider {
    Invoice generateInvoice(Order order);
}
