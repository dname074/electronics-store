package pl.javakurs.dname074.order.domain;

import pl.javakurs.dname074.order.model.Order;

public interface KafkaSenderProvider {
    void sendCreatedOrdersEvent(Order order);
}
