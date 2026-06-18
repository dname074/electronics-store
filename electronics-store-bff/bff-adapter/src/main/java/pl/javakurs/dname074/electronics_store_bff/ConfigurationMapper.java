package pl.javakurs.dname074.electronics_store_bff;

import org.mapstruct.Mapper;
import pl.javakurs.dname074.bff.dto.ConfigurationDto;
import pl.javakurs.dname074.bff.model.Configuration;

@Mapper(componentModel = "spring")
public interface ConfigurationMapper {
    ConfigurationDto toDto(Configuration pojo);
    Configuration toPojo(ConfigurationDto dto);
}
