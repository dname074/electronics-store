package pl.javakurs.dname074.order_service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.javakurs.dname074.order.dto.OrderProductDto;
import pl.javakurs.dname074.order.model.OrderProduct;
import pl.javakurs.dname074.order_service.entity.OrderProductEntity;

@Mapper(componentModel = "spring", uses = ConfigurationMapper.class)
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    OrderProduct toPojo(OrderProductDto dto);
    OrderProduct entityToPojo(OrderProductEntity entity);
    OrderProductEntity toEntity(OrderProduct pojo);
    OrderProductDto toDto(OrderProduct pojo);
}
