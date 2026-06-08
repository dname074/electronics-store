package pl.javakurs.dname074.order.domain;

import pl.javakurs.dname074.order.model.Order;
import pl.javakurs.dname074.order.model.PagePojo;

public interface OrderRepositoryProvider {
    Order save(Order order);

    PagePojo<Order> getOrders(Integer page, Integer size);
}
