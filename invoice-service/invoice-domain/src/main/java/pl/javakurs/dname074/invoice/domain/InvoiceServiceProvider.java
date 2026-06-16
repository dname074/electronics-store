package pl.javakurs.dname074.invoice.domain;

import pl.javakurs.dname074.invoice.model.Invoice;
import pl.javakurs.dname074.invoice.model.Order;
import pl.javakurs.dname074.invoice.model.PagePojo;

public interface InvoiceServiceProvider {
    Invoice generateInvoice(Order order);

    PagePojo<Invoice> getInvoices(int page, int size);
}
