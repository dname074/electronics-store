package pl.javakurs.dname074.order_service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.javakurs.dname074.order.domain.OrderServiceProvider;
import pl.javakurs.dname074.order.dto.CreateOrderCommand;
import pl.javakurs.dname074.order.dto.OrderDto;
import pl.javakurs.dname074.order.dto.PageDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {
    private final OrderServiceProvider orderService;
    private final PageMapper pageMapper;
    private final OrderMapper orderMapper;

    @PostMapping
    public OrderDto createOrder(@RequestBody CreateOrderCommand orderCommand) {

        return orderMapper.toDto(orderService.createOrder(orderCommand.cartId()));
    }

    @GetMapping
    public PageDto<OrderDto> getOrdersHistory(@RequestParam Integer page, @RequestParam Integer size) {
        return pageMapper.toDto(orderService.getOrdersHistory(page, size), orderMapper::toDto);
    }
}
