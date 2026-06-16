package pl.javakurs.dname074.invoice_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.javakurs.dname074.invoice.domain.InvoiceServiceProvider;
import pl.javakurs.dname074.invoice.model.Invoice;
import pl.javakurs.dname074.invoice.model.Order;
import pl.javakurs.dname074.invoice.model.PagePojo;

@RequiredArgsConstructor
@Component
public class InvoiceServiceFacade {
    private final InvoiceServiceProvider service;

    @Transactional
    public Invoice generateInvoice(Order order) {
        return service.generateInvoice(order);
    }

    public PagePojo<Invoice> getInvoices(int page, int size) {
        return service.getInvoices(page, size);
    }
}
