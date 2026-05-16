package pl.javakurs.dname074.domain;

import pl.javakurs.dname074.model.Configuration;

import java.util.Optional;

public interface ConfigurationRepositoryProvider {
    Optional<Configuration> findById(Long configurationId);
}
