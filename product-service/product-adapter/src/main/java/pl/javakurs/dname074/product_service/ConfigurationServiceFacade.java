package pl.javakurs.dname074.product_service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.javakurs.dname074.domain.ConfigurationServiceProvider;
import pl.javakurs.dname074.model.Configuration;

@Service
@RequiredArgsConstructor
public class ConfigurationServiceFacade {
    private final ConfigurationServiceProvider configurationService;

    @Transactional
    public Configuration addConfiguration(Configuration configuration, Long productId) {
        return configurationService.addConfiguration(configuration, productId);
    }
}
