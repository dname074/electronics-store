package pl.javakurs.dname074.cart_service;

import org.mapstruct.Mapper;
import pl.javakurs.dname074.cart.dto.CartDto;
import pl.javakurs.dname074.cart.model.Cart;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface CartMapper {
    CartDto toDto(Cart cart);
}
