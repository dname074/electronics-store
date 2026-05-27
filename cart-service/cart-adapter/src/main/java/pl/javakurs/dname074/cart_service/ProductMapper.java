package pl.javakurs.dname074.cart_service;

import org.mapstruct.Mapper;
import pl.javakurs.dname074.cart.dto.CartProductDto;
import pl.javakurs.dname074.cart.dto.ConfigurationDto;
import pl.javakurs.dname074.cart.model.CartProduct;
import pl.javakurs.dname074.cart.model.Configuration;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    CartProduct toPojo(CartProductDto product);
    CartProductDto toDto(CartProductDto productDto);
    Configuration configurationToPojo(ConfigurationDto configurationDto);
    ConfigurationDto configurationToDto(Configuration configuration);
}
