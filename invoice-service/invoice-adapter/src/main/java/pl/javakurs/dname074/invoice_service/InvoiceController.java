package pl.javakurs.dname074.invoice_service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.javakurs.dname074.invoice.dto.ExceptionResponseDto;
import pl.javakurs.dname074.invoice.dto.InvoiceDto;
import pl.javakurs.dname074.invoice.dto.OrderDto;
import pl.javakurs.dname074.invoice.dto.PageDto;
import pl.javakurs.dname074.invoice.dto.ValidExceptionResponseDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/invoices")
@Slf4j
class InvoiceController {
    private final InvoiceServiceFacade invoiceService;
    private final OrderMapper orderMapper;
    private final InvoiceMapper invoiceMapper;
    private final PageMapper pageMapper;

    @Operation(summary = "Generate invoice manually")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Invoice generated",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = InvoiceDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(oneOf = {
                                            ExceptionResponseDto.class,
                                            ValidExceptionResponseDto.class
                                    }))
                    })
    })
    @PostMapping
    public InvoiceDto generateInvoice(@RequestBody @Valid OrderDto order) {
        log.info("Received POST /invoices request with body: {}",
                order.toString());
        return invoiceMapper.toDto(invoiceService.generateInvoice(orderMapper.toPojo(order)));
    }

    @Operation(summary = "Get invoices page based on params")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invoices page found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PageDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(oneOf = {
                                            ExceptionResponseDto.class,
                                            ValidExceptionResponseDto.class
                                    }))
                    })
    })
    @GetMapping
    public PageDto<InvoiceDto> getInvoices(@ParameterObject Pageable pageable) {
        log.info("Received GET /invoices request with params: page = {}, size = {}",
                pageable.getPageNumber(), pageable.getPageSize());
        return pageMapper.toDto(invoiceService.getInvoices(pageable.getPageNumber(), pageable.getPageSize()), invoiceMapper::toDto);
    }
}
