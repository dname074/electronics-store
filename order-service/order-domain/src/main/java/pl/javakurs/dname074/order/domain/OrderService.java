package pl.javakurs.dname074.order.domain;

import lombok.RequiredArgsConstructor;
import pl.javakurs.dname074.order.model.Order;
import pl.javakurs.dname074.order.model.OrderCart;
import pl.javakurs.dname074.order.model.OrderStatus;
import pl.javakurs.dname074.order.model.PagePojo;

import java.time.Instant;

@RequiredArgsConstructor
public class OrderService implements OrderServiceProvider {
    private final CartClientProvider cartClient;
    private final OrderRepositoryProvider orderRepository;

    @Override
    public Order createOrder(String cartId) {
        OrderCart cart = cartClient.getCart(cartId);
        Order order = new Order(null, OrderStatus.CREATED, cart.getTotalPrice(),
                cart.getProducts(), Instant.now(), Instant.now());
        return orderRepository.save(order);
    }

    @Override
    public PagePojo<Order> getOrdersHistory(Integer page, Integer size) {
        return orderRepository.getOrders(page, size);
    }
}
