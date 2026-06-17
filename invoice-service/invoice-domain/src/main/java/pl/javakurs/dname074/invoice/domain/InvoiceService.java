package pl.javakurs.dname074.invoice.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.javakurs.dname074.invoice.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class InvoiceService implements InvoiceServiceProvider {
    private final int PAYMENT_TIME; // in days
    private final int DEFAULT_TAX;
    private final String KIND;
    private final String CURRENCY;
    private final String SELLER_NAME;
    private final String FIRST_PART_DOWNLOAD_URL;
    private final String SECOND_PART_DOWNLOAD_URL;

    private final InvoiceClientProvider client;
    private final InvoiceRepositoryProvider repository;

    @Override
    public Invoice generateInvoice(Order order) {
        log.info("Process of generating invoice has started");
        LocalDate date = extractDate(order.getCreatedAt());
        List<InvoiceProduct> products = prepareProducts(order.getProducts());

        CreateInvoiceCommand invoiceRequest = createInvoiceRequest(date, order.getCustomer(),
                order.getTotalPrice(), products);

        Invoice invoice = generateAndPersistInvoice(order.getId(), invoiceRequest);

        log.info("Process of generating invoice has ended");
        return invoice;
    }

    @Override
    public PagePojo<Invoice> getInvoices(int page, int size) {
        log.info("Process of getting invoices has started");
        PagePojo<Invoice> invoicesPage = repository.findAll(page, size);
        invoicesPage.getContent()
                        .forEach(
                                invoice -> invoice.setPdfDownloadUrl(FIRST_PART_DOWNLOAD_URL + invoice.getExternalProviderId() + SECOND_PART_DOWNLOAD_URL)
                        );
        log.info("Process of getting invoices has ended");
        return invoicesPage;
    }

    private LocalDate extractDate(Instant date) {
        return Instant
                .parse(date.toString())
                .atZone(ZoneOffset.UTC)
                .toLocalDate();
    }

    private List<InvoiceProduct> prepareProducts(List<InvoiceProduct> products) {
        return products.stream()
                .map(p -> new InvoiceProduct(p.getName(), DEFAULT_TAX, p.getTotalPriceGross(), 1))
                .toList();
    }

    private CreateInvoiceCommand createInvoiceRequest(LocalDate date, Customer customer,
                                                      BigDecimal totalPrice, List<InvoiceProduct> products) {
        return CreateInvoiceCommand.builder()
                .kind(KIND)
                .sellDate(date)
                .issueDate(date)
                .paymentTo(date.plusDays(PAYMENT_TIME))
                .sellerName(SELLER_NAME)
                .buyerName(String.format("%s %s", customer.getFirstName(), customer.getLastName()))
                .buyerCountry(customer.getCountry())
                .buyerCity(customer.getTown())
                .buyerPostCode(customer.getPostalCode())
                .buyerStreet(customer.getStreet())
                .priceNet(calculateNetPrice(totalPrice))
                .priceGross(totalPrice)
                .currency(CURRENCY)
                .positions(products)
                .build();
    }

    private BigDecimal calculateNetPrice(BigDecimal grossPrice) {
        BigDecimal divider = BigDecimal.valueOf(DEFAULT_TAX)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
                .add(BigDecimal.ONE);
        return grossPrice.divide(divider, 2, RoundingMode.HALF_UP);
    }

    private Invoice generateAndPersistInvoice(Long orderId, CreateInvoiceCommand invoiceRequest) {
        Invoice invoice = client.generateInvoice(invoiceRequest);
        if (invoice.getExternalProviderId() == null) {
            log.info("Fallback invoice returned");
            return invoice;
        }
        invoice.fulfillData(orderId);
        Invoice dbInvoice = repository.save(invoice);
        invoice.setId(dbInvoice.getId());
        return invoice;
    }
}
