package pl.javakurs.dname074.order.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.javakurs.dname074.order.model.*;
import pl.javakurs.dname074.order.model.exception.MissingResourceException;

import java.time.Instant;

@RequiredArgsConstructor
@Slf4j
public class OrderService implements OrderServiceProvider {
    private final CartClientProvider cartClient;
    private final OrderRepositoryProvider orderRepository;
    private final KafkaSenderProvider kafkaSender;

    @Override
    public Order createOrder(String cartId, Customer customer) {
        log.info("Process of creating order has started");
        if (customer == null) {
            throw new MissingResourceException("Customer data has not been provided");
        }
        OrderCart cart = cartClient.getCart(cartId);
        System.out.println(cart.getProducts().getFirst().getConfigurationSnapshot());
        Order order = new Order(null, OrderStatus.CREATED, cart.getTotalPrice(),
                cart.getProducts(), customer, Instant.now(), Instant.now());
        customer.setOrder(order);
        order = orderRepository.save(order);
        OrderCart removedCart = cartClient.removeCart(cartId);
        log.info("Cart has been removed: {}", removedCart);
        kafkaSender.sendCreatedOrdersEvent(order);
        log.info("Process of creating order has ended");
        return order;
    }

    @Override
    public PagePojo<Order> getOrdersHistory(Integer page, Integer size) {
        log.info("Process of receiving orders history has started");
        PagePojo<Order> ordersHistory = orderRepository.getOrders(page, size);
        log.info("Process of receiving orders history has ended");
        return ordersHistory;
    }
}
