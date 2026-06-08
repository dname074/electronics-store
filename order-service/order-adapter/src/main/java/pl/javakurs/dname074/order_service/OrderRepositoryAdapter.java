package pl.javakurs.dname074.order_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.javakurs.dname074.order.domain.OrderRepositoryProvider;
import pl.javakurs.dname074.order.model.Order;
import pl.javakurs.dname074.order.model.PagePojo;

@Component
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepositoryProvider {
    private final OrderRepository repository;
    private final OrderMapper orderMapper;

    @Override
    public Order save(Order order) {
        return orderMapper.entityToPojo(repository.save(orderMapper.toEntity(order)));
    }

    @Override
    public PagePojo<Order> getOrders(Integer page, Integer size) {
        return null;
    }
}
