package pl.javakurs.dname074.invoice_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.javakurs.dname074.invoice.domain.InvoiceRepositoryProvider;
import pl.javakurs.dname074.invoice.model.Invoice;

@RequiredArgsConstructor
@Component
public class InvoiceRepositoryAdapter implements InvoiceRepositoryProvider {
    private final InvoiceRepository repository;
    private final InvoiceMapper invoiceMapper;

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceMapper.entityToPojo(repository.save(invoiceMapper.pojoToEntity(invoice)));
    }
}
