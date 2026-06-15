package pl.javakurs.dname074.invoice_service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.javakurs.dname074.invoice.dto.OrderDto;

@Component
@RequiredArgsConstructor
public class InvoiceListener {
    private final InvoiceServiceFacade invoiceService;
    private final OrderMapper orderMapper;

    @KafkaListener(topics = "created-orders")
    public void listenForCreatedOrders(OrderDto orderCreated) {
        invoiceService.generateInvoice(orderMapper.toPojo(orderCreated));
    }
}
