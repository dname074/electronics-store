package pl.javakurs.dname074.electronics_store_bff;

import org.mapstruct.Mapper;
import pl.javakurs.dname074.bff.dto.ProductDto;
import pl.javakurs.dname074.bff.model.Product;

@Mapper(componentModel = "spring", uses = ConfigurationMapper.class)
interface ProductMapper {
    ProductDto toDto(Product product);
    Product toPojo(ProductDto product);
}
