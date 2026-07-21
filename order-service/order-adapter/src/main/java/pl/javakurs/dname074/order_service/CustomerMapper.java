package pl.javakurs.dname074.order_service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.javakurs.dname074.order.dto.CreateCustomerCommand;
import pl.javakurs.dname074.order.dto.CustomerDto;
import pl.javakurs.dname074.order.model.Customer;
import pl.javakurs.dname074.order_service.entity.OrderCustomerEntity;

@Mapper(componentModel = "spring")
interface CustomerMapper {
//    @Mapping(target = "id", ignore = true)
    Customer commandToPojo(CreateCustomerCommand command);
    @Mapping(target = "order", ignore = true)
    OrderCustomerEntity toEntity(Customer pojo);
    @Mapping(target = "order", ignore = true)
    Customer entityToPojo(OrderCustomerEntity entity);
    CustomerDto toDto(Customer pojo);
}
