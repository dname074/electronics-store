package pl.javakurs.dname074.order_service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import pl.javakurs.dname074.order.domain.OrderRepositoryProvider;
import pl.javakurs.dname074.order.model.Order;
import pl.javakurs.dname074.order.model.PagePojo;
import pl.javakurs.dname074.order_service.entity.OrderEntity;

@Component
@RequiredArgsConstructor
class OrderRepositoryAdapter implements OrderRepositoryProvider {
    private final OrderRepository repository;
    private final OrderMapper orderMapper;
    private final PageMapper pageMapper;

    @Override
    public Order save(Order order) {
        return orderMapper.entityToPojo(repository.save(orderMapper.toEntity(order)));
    }

    @Override
    public PagePojo<Order> getOrders(Integer page, Integer size) {
        Page<OrderEntity> ordersPage = repository.findAll(PageRequest.of(page, size));
        return pageMapper.entityToPojo(ordersPage, orderMapper::entityToPojo);
    }
}
