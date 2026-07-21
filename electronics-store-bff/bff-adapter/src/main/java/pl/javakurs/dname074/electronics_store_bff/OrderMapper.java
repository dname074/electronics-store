package pl.javakurs.dname074.electronics_store_bff;

import org.mapstruct.Mapper;
import pl.javakurs.dname074.bff.dto.OrderDto;
import pl.javakurs.dname074.bff.model.Order;

@Mapper(componentModel = "spring", uses = CustomerMapper.class)
interface OrderMapper {
    OrderDto toDto(Order pojo);
    Order toPojo(OrderDto dto);
}
