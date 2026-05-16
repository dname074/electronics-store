package pl.javakurs.dname074.product_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.javakurs.dname074.domain.ConfigurationRepositoryProvider;
import pl.javakurs.dname074.model.Configuration;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ConfigurationRepositoryAdapter implements ConfigurationRepositoryProvider {
    private final ConfigurationRepository repository;
    private final ConfigurationMapper configurationMapper;

    @Override
    public Optional<Configuration> findById(Long configurationId) {
        return repository.findById(configurationId)
                .map(configurationMapper::entityToPojo);
    }
}
