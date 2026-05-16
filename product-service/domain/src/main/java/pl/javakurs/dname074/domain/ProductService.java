package pl.javakurs.dname074.domain;

import lombok.RequiredArgsConstructor;
import pl.javakurs.dname074.model.ConfigType;
import pl.javakurs.dname074.model.Configuration;
import pl.javakurs.dname074.model.PagePojo;
import pl.javakurs.dname074.model.Product;
import pl.javakurs.dname074.model.ProductConfiguration;
import pl.javakurs.dname074.model.exception.AlreadyContainsDefaultConfigException;
import pl.javakurs.dname074.model.exception.ResourceAlreadyExistsException;
import pl.javakurs.dname074.model.exception.ResourceNotFoundException;

import java.util.List;

@RequiredArgsConstructor
public class ProductService implements ProductServiceProvider {
    private final ProductRepositoryProvider productRepository;
    private final ConfigurationRepositoryProvider configurationRepository;

    @Override
    public PagePojo<Product> getProductsPage(int page, int size) {
        return productRepository.findAll(page, size);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with provided id does not exist."));
    }

    @Override
    public Product addProduct(Product product) {
        if (productRepository.findBySku(product.getSku()).isPresent()) {
            throw new ResourceAlreadyExistsException("Product with provided sku, had been already added.");
        }
        return productRepository.save(product);
    }

    @Override
    public Product modifyProduct(Long id, Product modifiedProduct) {
        Product product = getProduct(id);
        product.update(modifiedProduct);
        return productRepository.save(product);
    }

    @Override
    public Product addConfigurationToProduct(Long productId, Long configurationId, Boolean isDefault) {
        Product product = getProduct(productId);
        Configuration configuration = configurationRepository.findById(configurationId)
                .orElseThrow(() -> new ResourceNotFoundException("Configuration with provided id does not exist."));
        // param isDefault will be set to true, when provided configuration's type wasn't added before
        if (isDefault && containsDefaultConfiguration(product.getConfigurations(), configuration.getType())) {
            throw new AlreadyContainsDefaultConfigException("Selected product already contains default configuration of provided config type.");
        }

        ProductConfiguration productConfiguration = new ProductConfiguration(product, configuration, isDefault);
        product.addConfiguration(productConfiguration);
        return productRepository.save(product);
    }

    private Boolean containsDefaultConfiguration(List<ProductConfiguration> configurationList, ConfigType configType) {
            return configurationList.stream()
                    .filter(productConfiguration -> productConfiguration.getIsDefault()==true)
                    .map(ProductConfiguration::getConfiguration)
                    .anyMatch(configuration -> configuration.getType().equals(configType));
    }
}
