package pl.javakurs.dname074.invoice_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.javakurs.dname074.invoice.domain.InvoiceServiceProvider;
import pl.javakurs.dname074.invoice.model.Invoice;
import pl.javakurs.dname074.invoice.model.Order;

@RequiredArgsConstructor
@Component
public class InvoiceServiceFacade {
    private final InvoiceServiceProvider service;

    @Transactional
    public Invoice generateInvoice(Order order) {
        return service.generateInvoice(order);
    }
}
