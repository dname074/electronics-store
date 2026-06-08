package pl.javakurs.dname074.order_service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.javakurs.dname074.order.domain.OrderServiceProvider;
import pl.javakurs.dname074.order.dto.CreateOrderCommand;
import pl.javakurs.dname074.order.model.Order;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderServiceProvider orderService;

    @PostMapping
    public Order createOrder(@RequestBody CreateOrderCommand orderCommand) {
        return orderService.createOrder(orderCommand.cartId());
    }

//    @GetMapping
//    public PageDto<OrderDto> getOrdersHistory(@RequestParam Integer page, @RequestParam Integer size) {
//        return orderService.getOrdersHistory(page, size);
//    }
}
