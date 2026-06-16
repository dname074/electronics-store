package pl.javakurs.dname074.invoice_service;

import org.mapstruct.Mapper;
import pl.javakurs.dname074.invoice.dto.OrderDto;
import pl.javakurs.dname074.invoice.model.Order;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, CustomerMapper.class})
interface OrderMapper {
    Order toPojo(OrderDto dto);
}
