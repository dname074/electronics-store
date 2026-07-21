package pl.javakurs.dname074.electronics_store_bff;

import org.mapstruct.Mapper;
import pl.javakurs.dname074.bff.dto.CartDto;
import pl.javakurs.dname074.bff.model.Cart;

@Mapper(componentModel = "spring")
interface CartMapper {
    CartDto toDto(Cart pojo);
    Cart toPojo(CartDto dto);
}
