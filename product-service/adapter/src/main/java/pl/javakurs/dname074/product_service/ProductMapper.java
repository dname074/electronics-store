package pl.javakurs.dname074.product_service;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import pl.javakurs.dname074.dto.ConfigurationDto;
import pl.javakurs.dname074.dto.CreateProductCommand;
import pl.javakurs.dname074.dto.ProductDto;
import pl.javakurs.dname074.model.Product;
import pl.javakurs.dname074.model.ProductConfiguration;
import pl.javakurs.dname074.product_service.entity.ProductConfigurationEntity;
import pl.javakurs.dname074.product_service.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = ConfigurationMapper.class)
public abstract class ProductMapper {
    @Autowired
    protected ConfigurationMapper configurationMapper;

    @Mapping(target = "configurations", qualifiedByName = "configurationsToDto")
    public abstract ProductDto pojoToDto(Product product);

    public abstract Product entityToPojo(ProductEntity product);
    public abstract Product dtoToPojo(CreateProductCommand product);

    @Mapping(target = "configurations", ignore = true)
    public abstract ProductEntity pojoToEntity(Product product);

    @Mapping(target = "productConfigurationId", ignore = true)
    public abstract ProductConfigurationEntity productConfigurationToEntity(ProductConfiguration value);

    public abstract ProductConfiguration productConfigurationToPojo(ProductConfigurationEntity value);

    @Named(value = "configurationsToDto")
    List<ConfigurationDto> configurationsToDto(List<ProductConfiguration> configurations) {
        if (configurations == null) return null;
        return configurations.stream()
                .map(pc -> configurationMapper.toDto(pc.getConfiguration()))
                .collect(Collectors.toList());
    }

    @AfterMapping
    protected void fillProductConfigurations(
            Product source,
            @MappingTarget ProductEntity target) {

        if (source.getConfigurations() == null) return;

        List<ProductConfigurationEntity> entities = source.getConfigurations().stream()
                .map(pc -> {
                    ProductConfigurationEntity entity = productConfigurationToEntity(pc);
                    entity.setProduct(target); // ustawiamy referencję do rodzica
                    return entity;
                })
                .collect(Collectors.toList());

        target.setConfigurations(entities);
    }
}
