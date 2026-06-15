package pl.javakurs.dname074.invoice_service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.javakurs.dname074.invoice.dto.InvoiceDto;
import pl.javakurs.dname074.invoice.dto.OrderDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    private final InvoiceServiceFacade invoiceService;
    private final OrderMapper orderMapper;
    private final InvoiceMapper invoiceMapper;

    @PostMapping
    public InvoiceDto generateInvoice(@RequestBody OrderDto order) {
        return invoiceMapper.toDto(invoiceService.generateInvoice(orderMapper.toPojo(order)));
    }
}
