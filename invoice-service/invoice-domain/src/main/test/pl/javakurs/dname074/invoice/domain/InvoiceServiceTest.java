package pl.javakurs.dname074.invoice.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.javakurs.dname074.invoice.model.Customer;
import pl.javakurs.dname074.invoice.model.Invoice;
import pl.javakurs.dname074.invoice.model.InvoiceProduct;
import pl.javakurs.dname074.invoice.model.Order;
import pl.javakurs.dname074.invoice.model.PagePojo;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvoiceServiceTest {
    @Mock
    private InvoiceClientProvider client;
    @Mock
    private InvoiceRepositoryProvider repository;
    private InvoiceService service;

    @BeforeEach
    void setup() {
        this.service = new InvoiceService(7, 23, "vat",
                "PLN", "Elec-Store", "https://electronics-store-test-project.fakturownia.pl/invoices/",
                ".pdf?api_token=bwzuekW26k6ZpfrSJ1kb", client, repository);
    }

    @Test
    void generateInvoice_DataCorrect_GenerateAndPersistInvoice() {
        Customer customer = new Customer(1L, "Jan", "Kowalski", "Poland",
                "Warszawa", "12-345", "Testowa", "1");
        InvoiceProduct product = new InvoiceProduct("Produkt", 23,
                BigDecimal.valueOf(123), 1);
        Order order = new Order(1L, BigDecimal.valueOf(123), List.of(product),
                customer, Instant.parse("2026-01-10T12:00:00Z"), Instant.parse("2026-01-10T12:00:00Z"));
        Invoice generatedInvoice = new Invoice(null, 53425L, null,
                "01/02/2026", "ISSUED", null, null);
        Invoice savedInvoice = new Invoice(1L, 53425L, 1L,
                "01/02/2026", "ISSUED", null, null);

        when(client.generateInvoice(any()))
                .thenReturn(generatedInvoice);
        when(repository.save(any()))
                .thenReturn(savedInvoice);

        Invoice result = service.generateInvoice(order);

        assertEquals(1L, result.getId());
        assertEquals(53425L, result.getExternalProviderId());

        verify(client, times(1)).generateInvoice(any());
        verify(repository, times(1)).save(any());
    }

    @Test
    void getInvoices_DataCorrect_InvoicesPageReturned() {
        Invoice invoice = new Invoice(1L, 53425L, 1L,
                "01/02/2026", "ISSUED", null, null);
        PagePojo<Invoice> page = new PagePojo<>(List.of(invoice), 1, 1L, 0, 10);

        when(repository.findAll(0, 10)).thenReturn(page);

        PagePojo<Invoice> result = service.getInvoices(0, 10);

        assertEquals(List.of(invoice), result.getContent());
        assertEquals(
                "https://electronics-store-test-project.fakturownia.pl/invoices/53425.pdf?api_token=bwzuekW26k6ZpfrSJ1kb",
                result.getContent().getFirst().getPdfDownloadUrl()
        );

        verify(repository).findAll(0, 10);
    }
}
