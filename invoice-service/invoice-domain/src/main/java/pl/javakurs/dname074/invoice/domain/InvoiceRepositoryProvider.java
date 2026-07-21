package pl.javakurs.dname074.invoice.domain;

import pl.javakurs.dname074.invoice.model.Invoice;
import pl.javakurs.dname074.invoice.model.PagePojo;

public interface InvoiceRepositoryProvider {
    Invoice save(Invoice invoice);

    PagePojo<Invoice> findAll(int page, int size);
}
