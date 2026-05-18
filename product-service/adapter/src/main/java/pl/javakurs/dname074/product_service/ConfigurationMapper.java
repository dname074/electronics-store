package pl.javakurs.dname074.product_service;

import org.mapstruct.Mapper;
import pl.javakurs.dname074.dto.ConfigurationDto;
import pl.javakurs.dname074.dto.CreateConfigurationCommand;
import pl.javakurs.dname074.model.Configuration;
import pl.javakurs.dname074.product_service.entity.ConfigurationEntity;

@Mapper(componentModel = "spring")
interface ConfigurationMapper {
    ConfigurationDto toDto(Configuration configuration);

    Configuration entityToPojo(ConfigurationEntity configuration);

    Configuration dtoToPojo(CreateConfigurationCommand configuration);

    ConfigurationEntity pojoToEntity(Configuration configuration);
}
