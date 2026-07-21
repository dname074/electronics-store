package pl.javakurs.dname074.order_service;

import org.mapstruct.Mapper;
import pl.javakurs.dname074.order.dto.OrderCartDto;
import pl.javakurs.dname074.order.model.OrderCart;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
interface CartMapper {
    OrderCart toPojo(OrderCartDto dto);
    OrderCartDto toDto(OrderCart pojo);
}
