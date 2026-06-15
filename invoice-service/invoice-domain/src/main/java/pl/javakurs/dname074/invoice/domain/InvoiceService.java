package pl.javakurs.dname074.invoice.domain;

import lombok.RequiredArgsConstructor;
import pl.javakurs.dname074.invoice.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

@RequiredArgsConstructor
public class InvoiceService implements InvoiceServiceProvider {
    private final int PAYMENT_TIME; // in days
    private final int DEFAULT_TAX;
    private final String KIND;
    private final String CURRENCY;
    private final String SELLER_NAME;

    private final InvoiceClientProvider client;
    private final InvoiceRepositoryProvider repository;

    @Override
    public Invoice generateInvoice(Order order) {
        LocalDate date = extractDate(order.getCreatedAt());
        List<InvoiceProduct> products = prepareProducts(order.getProducts());

        CreateInvoiceCommand invoiceRequest = createInvoice(date, order.getCustomer(),
                order.getTotalPrice(), products);
        Invoice invoice = client.generateInvoice(invoiceRequest);
        invoice.fulfillData(order.getId());
        Invoice dbInvoice = repository.save(invoice);
        invoice.setId(dbInvoice.getId());
        return invoice;
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

    private CreateInvoiceCommand createInvoice(LocalDate date, Customer customer,
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
}
