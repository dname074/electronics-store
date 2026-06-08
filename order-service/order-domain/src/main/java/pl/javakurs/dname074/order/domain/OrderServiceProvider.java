package pl.javakurs.dname074.order.domain;

import pl.javakurs.dname074.order.model.Order;
import pl.javakurs.dname074.order.model.PagePojo;

public interface OrderServiceProvider {
    Order createOrder(String cartId);
    PagePojo<Order> getOrdersHistory(Integer page, Integer size);
}
