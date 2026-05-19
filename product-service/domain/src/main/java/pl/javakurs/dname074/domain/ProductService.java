package pl.javakurs.dname074.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.javakurs.dname074.model.ConfigType;
import pl.javakurs.dname074.model.Configuration;
import pl.javakurs.dname074.model.PagePojo;
import pl.javakurs.dname074.model.Product;
import pl.javakurs.dname074.model.ProductConfiguration;
import pl.javakurs.dname074.model.ProductType;
import pl.javakurs.dname074.model.exception.AlreadyContainsDefaultConfigException;
import pl.javakurs.dname074.model.exception.ResourceAlreadyExistsException;
import pl.javakurs.dname074.model.exception.ResourceNotFoundException;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ProductService implements ProductServiceProvider {
    private final ProductRepositoryProvider productRepository;
    private final ConfigurationRepositoryProvider configurationRepository;

    @Override
    public PagePojo<Product> getProductsPage(int page, int size, ProductType type) {
        log.info("Process of getting products page has started");
        PagePojo<Product> productsPage = productRepository.findAll(page, size, type);
        log.info("Process of getting products page has ended");
        return productsPage;
    }

    @Override
    public Product getProduct(Long id) {
        log.info("Process of getting product has started");
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with provided id does not exist."));
        log.info("Process of getting product has ended");
        return product;
    }

    @Override
    public Product addProduct(Product newProduct) {
        log.info("Process of adding new product has started");
        if (productRepository.findBySku(newProduct.getSku()).isPresent()) {
            throw new ResourceAlreadyExistsException("Product with provided sku, had been already added.");
        }
        Product product = productRepository.save(newProduct);
        log.info("Process of adding new product has ended");
        return product;
    }

    @Override
    public Product modifyProduct(Long id, Product modifiedProduct) {
        log.info("Process of modifying existing product has started");
        Product product = getProduct(id);
        product.update(modifiedProduct);
        product = productRepository.save(product);
        log.info("Process of modifying existing product has ended");
        return product;
    }

    @Override
    public Product addConfigurationToProduct(Long productId, Long configurationId, Boolean isDefault) {
        log.info("Process of adding configuration to product has started");
        Product product = getProduct(productId);
        Configuration configuration = configurationRepository.findById(configurationId)
                .orElseThrow(() -> new ResourceNotFoundException("Configuration with provided id does not exist."));
        // param isDefault will be set to true, when provided configuration's type wasn't added before
        if (isDefault && containsDefaultConfiguration(product.getConfigurations(), configuration.getType())) {
            throw new AlreadyContainsDefaultConfigException("Selected product already contains default configuration of provided config type.");
        }

        ProductConfiguration productConfiguration = new ProductConfiguration(configuration, isDefault);
        product.addConfiguration(productConfiguration);
        product = productRepository.save(product);
        log.info("Process of adding configuration to product has ended");
        return product;
    }

    @Override
    public Product removeProduct(Long id) {
        log.info("Process of removing product has started");
        Product product = getProduct(id);
        productRepository.delete(product);
        log.info("Process of removing product has ended");
        return product;
    }

    private boolean containsDefaultConfiguration(List<ProductConfiguration> configurationList, ConfigType configType) {
            return configurationList.stream()
                    .filter(productConfiguration -> productConfiguration.getIsDefault()==true)
                    .map(ProductConfiguration::getConfiguration)
                    .anyMatch(configuration -> configuration.getType().equals(configType));
    }
}
