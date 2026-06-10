package pl.javakurs.dname074.order_service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.javakurs.dname074.order.domain.OrderServiceProvider;
import pl.javakurs.dname074.order.model.Order;
import pl.javakurs.dname074.order.model.PagePojo;

@Service
@RequiredArgsConstructor
public class OrderServiceFacade {
    private final OrderServiceProvider orderService;

    @Transactional
    public Order createOrder(String cartId) {
        return orderService.createOrder(cartId);
    }

    public PagePojo<Order> getOrdersHistory(Integer page, Integer size) {
        return orderService.getOrdersHistory(page, size);
    }
}
