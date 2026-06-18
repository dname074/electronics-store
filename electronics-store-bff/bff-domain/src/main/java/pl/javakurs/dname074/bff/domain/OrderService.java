package pl.javakurs.dname074.bff.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.javakurs.dname074.bff.model.Customer;
import pl.javakurs.dname074.bff.model.Order;

@RequiredArgsConstructor
@Slf4j
public class OrderService implements OrderServiceProvider {
    private final OrderClientProvider client;

    @Override
    public Order createOrder(String cartId, Customer customer) {
        log.info("Process of sending request to create an order has started");
        return client.createOrder(cartId, customer);
    }
}
