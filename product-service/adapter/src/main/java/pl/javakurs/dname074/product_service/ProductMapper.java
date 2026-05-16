package pl.javakurs.dname074.product_service;

import org.mapstruct.Mapper;
import pl.javakurs.dname074.dto.CreateProductCommand;
import pl.javakurs.dname074.dto.ProductDto;
import pl.javakurs.dname074.model.Product;
import pl.javakurs.dname074.model.ProductConfiguration;
import pl.javakurs.dname074.product_service.entity.ProductConfigurationEntity;
import pl.javakurs.dname074.product_service.entity.ProductEntity;

@Mapper(componentModel = "spring", uses = ConfigurationMapper.class)
public interface ProductMapper {
    ProductDto pojoToDto(Product product);
    Product entityToPojo(ProductEntity product);
    Product dtoToPojo(CreateProductCommand product);
    ProductEntity pojoToEntity(Product product);

    ProductConfiguration productConfigurationToPojo(ProductConfigurationEntity value);
    ProductConfigurationEntity productConfigurationToEntity(ProductConfiguration value);
}
