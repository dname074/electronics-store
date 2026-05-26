package pl.javakurs.dname074.cart_service;

import org.mapstruct.Mapper;
import pl.javakurs.dname074.cart.dto.CartProductDto;
import pl.javakurs.dname074.cart.model.CartProduct;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    CartProduct toPojo(CartProductDto product);
}
