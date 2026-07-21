package pl.javakurs.dname074.order_service;

import org.mapstruct.Mapper;
import pl.javakurs.dname074.order.dto.ConfigurationDto;
import pl.javakurs.dname074.order.model.Configuration;

@Mapper(componentModel = "spring")
public interface ConfigurationMapper {
    Configuration toPojo(ConfigurationDto dto);
    ConfigurationDto toDto(Configuration pojo);
}
