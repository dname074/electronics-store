package pl.javakurs.dname074.product_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.javakurs.dname074.domain.ConfigurationRepositoryProvider;
import pl.javakurs.dname074.model.ConfigType;
import pl.javakurs.dname074.model.Configuration;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class ConfigurationRepositoryAdapter implements ConfigurationRepositoryProvider {
    private final ConfigurationRepository repository;
    private final ConfigurationMapper configurationMapper;

    @Override
    public Optional<Configuration> findById(Long configurationId) {
        return repository.findById(configurationId)
                .map(configurationMapper::entityToPojo);
    }

    @Override
    public Configuration save(Configuration configuration) {
        return configurationMapper.entityToPojo(repository.save(configurationMapper.pojoToEntity(configuration)));
    }

    @Override
    public Boolean existsByNameAndType(String name, ConfigType type) {
        return repository.existsByNameAndType(name, type);
    }
}
