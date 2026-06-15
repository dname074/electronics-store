package pl.javakurs.dname074.invoice.domain;

import pl.javakurs.dname074.invoice.model.Invoice;

public interface InvoiceRepositoryProvider {
    Invoice save(Invoice invoice);
}
