package pl.javakurs.dname074.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.javakurs.dname074.model.Configuration;
import pl.javakurs.dname074.model.exception.InvalidProductTypeException;
import pl.javakurs.dname074.model.Product;
import pl.javakurs.dname074.model.exception.ResourceAlreadyExistsException;
import pl.javakurs.dname074.model.exception.ResourceNotFoundException;

@Slf4j
@RequiredArgsConstructor
public class ConfigurationService implements ConfigurationServiceProvider {
    private final ConfigurationRepositoryProvider configurationRepository;
    private final ProductRepositoryProvider productRepository;

    @Override
    public Configuration addConfiguration(Configuration configuration, Long productId) {
        log.info("Process of adding new configuration has started");
        if (productId == null && configurationRepository.existsByNameAndType(configuration.getName(), configuration.getType())) {
            throw new ResourceAlreadyExistsException("Provided configuration already exists");
        }

        if (productId == null) {
            return configurationRepository.save(configuration);
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product with provided id not found"));

        if (!isConfigTypeCorrect(product, configuration)) {
            throw new InvalidProductTypeException("Product type doesn't match provided configuration's type");
        }
        configuration.setProduct(product);
        log.info("Process of adding new configuration has ended");
        return configurationRepository.save(configuration);
    }

    private boolean isConfigTypeCorrect(Product product, Configuration configuration) {
        return product.getType().name().equals(configuration.getType().name());
    }
}
