package pl.javakurs.dname074.domain;

import pl.javakurs.dname074.model.Configuration;

public interface ConfigurationServiceProvider {
    Configuration addConfiguration(Configuration configuration, Long productId);
}
