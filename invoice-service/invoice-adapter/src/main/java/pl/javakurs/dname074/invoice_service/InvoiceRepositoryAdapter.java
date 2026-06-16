package pl.javakurs.dname074.invoice_service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import pl.javakurs.dname074.invoice.domain.InvoiceRepositoryProvider;
import pl.javakurs.dname074.invoice.model.Invoice;
import pl.javakurs.dname074.invoice.model.PagePojo;

@RequiredArgsConstructor
@Component
public class InvoiceRepositoryAdapter implements InvoiceRepositoryProvider {
    private final InvoiceRepository repository;
    private final InvoiceMapper invoiceMapper;
    private final PageMapper pageMapper;

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceMapper.entityToPojo(repository.save(invoiceMapper.pojoToEntity(invoice)));
    }

    @Override
    public PagePojo<Invoice> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return pageMapper.entityToPojo(repository.findAll(pageable), invoiceMapper::entityToPojo);
    }
}
