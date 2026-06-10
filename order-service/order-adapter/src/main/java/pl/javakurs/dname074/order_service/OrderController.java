package pl.javakurs.dname074.order_service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.javakurs.dname074.order.dto.CreateOrderCommand;
import pl.javakurs.dname074.order.dto.ExceptionResponseDto;
import pl.javakurs.dname074.order.dto.OrderDto;
import pl.javakurs.dname074.order.dto.PageDto;
import pl.javakurs.dname074.order.dto.ValidExceptionResponseDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {
    private final OrderServiceFacade orderService;
    private final PageMapper pageMapper;
    private final OrderMapper orderMapper;

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
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderDto createOrder(@RequestBody CreateOrderCommand orderCommand) {
        log.info("Received POST /orders request with body {}", orderCommand.cartId());
        return orderMapper.toDto(orderService.createOrder(orderCommand.cartId()));
    }

    @Operation(summary = "Get orders history")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders page returned",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PageDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Bad request, no parameter passed or wrong parameter passed",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(oneOf = {
                                            ExceptionResponseDto.class,
                                            ValidExceptionResponseDto.class
                                    }))
                    })
    })
    @GetMapping
    public PageDto<OrderDto> getOrdersHistory(@RequestParam Integer page, @RequestParam Integer size) {
        log.info("Received GET /orders request with params page={} and size={}", page, size);
        return pageMapper.toDto(orderService.getOrdersHistory(page, size), orderMapper::toDto);
    }
}
