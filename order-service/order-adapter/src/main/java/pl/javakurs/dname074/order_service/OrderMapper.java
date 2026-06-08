package pl.javakurs.dname074.order_service;

import org.mapstruct.Mapper;
import pl.javakurs.dname074.order.dto.OrderDto;
import pl.javakurs.dname074.order.model.Order;
import pl.javakurs.dname074.order_service.entity.OrderEntity;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface OrderMapper {
    Order dtoToPojo(OrderDto dto);
    Order entityToPojo(OrderEntity entity);
    OrderEntity toEntity(Order pojo);
    OrderDto toDto(Order pojo);
}
