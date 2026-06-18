package pl.javakurs.dname074.bff.domain;

import pl.javakurs.dname074.bff.model.Customer;
import pl.javakurs.dname074.bff.model.Order;

public interface OrderServiceProvider {
    Order createOrder(String cartId, Customer customer);
}
