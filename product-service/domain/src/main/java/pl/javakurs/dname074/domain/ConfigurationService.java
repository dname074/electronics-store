package pl.javakurs.dname074.domain;

import lombok.RequiredArgsConstructor;
import pl.javakurs.dname074.model.Configuration;

@RequiredArgsConstructor
public class ConfigurationService implements ConfigurationServiceProvider {
    private final ConfigurationRepositoryProvider configurationRepository;

    @Override
    public Configuration addConfiguration(Configuration configuration) {
        // todo

        return null;
    }
}
