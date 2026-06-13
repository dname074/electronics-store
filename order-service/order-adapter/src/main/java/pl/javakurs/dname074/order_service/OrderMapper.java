package pl.javakurs.dname074.order_service;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pl.javakurs.dname074.order.dto.OrderDto;
import pl.javakurs.dname074.order.model.Order;
import pl.javakurs.dname074.order_service.entity.OrderEntity;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, CustomerMapper.class})
public interface OrderMapper {
    Order dtoToPojo(OrderDto dto);
    Order entityToPojo(OrderEntity entity);
    OrderEntity toEntity(Order pojo);
    OrderDto toDto(Order pojo);

    @AfterMapping
    default void afterMapping(
            @MappingTarget OrderEntity order
    ) {
        if (order.getCustomer() != null) {
            order.getCustomer().setOrder(order);
        }
    }

    @AfterMapping
    default void afterMapping(
            @MappingTarget Order order
    ) {
        if (order.getCustomer() != null) {
            order.getCustomer().setOrder(order);
        }
    }
}
