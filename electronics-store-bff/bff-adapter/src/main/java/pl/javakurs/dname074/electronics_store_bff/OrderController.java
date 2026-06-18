package pl.javakurs.dname074.electronics_store_bff;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.javakurs.dname074.bff.domain.OrderServiceProvider;
import pl.javakurs.dname074.bff.dto.CreateOrderCommand;
import pl.javakurs.dname074.bff.dto.ExceptionResponseDto;
import pl.javakurs.dname074.bff.dto.OrderDto;
import pl.javakurs.dname074.bff.dto.ValidExceptionResponseDto;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
class OrderController {
    private final OrderServiceProvider service;
    private final OrderMapper orderMapper;
    private final CustomerMapper customerMapper;

    @Operation(summary = "Create order with cart id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart found and order created",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrderDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Bad request, no parameter passed or wrong parameter passed",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(oneOf = {
                                            ExceptionResponseDto.class,
                                            ValidExceptionResponseDto.class
                                    }))
                    }),
            @ApiResponse(responseCode = "404", description = "Cart not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponseDto.class))
                    }),
    })
    @PostMapping
    public OrderDto createOrder(@RequestBody @Valid CreateOrderCommand order) {
        log.info("Received POST /api/v1/orders request with body: {}", order.toString());
        return orderMapper.toDto(service.createOrder(order.cartId(), customerMapper.commandToPojo(order.customer())));
    }
}
