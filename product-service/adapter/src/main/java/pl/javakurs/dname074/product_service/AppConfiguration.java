package pl.javakurs.dname074.product_service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.javakurs.dname074.domain.ConfigurationRepositoryProvider;
import pl.javakurs.dname074.domain.ConfigurationService;
import pl.javakurs.dname074.domain.ConfigurationServiceProvider;
import pl.javakurs.dname074.domain.ProductRepositoryProvider;
import pl.javakurs.dname074.domain.ProductService;
import pl.javakurs.dname074.domain.ProductServiceProvider;

@Configuration
public class AppConfiguration {
    @Bean
    public ProductServiceProvider productService(ProductRepositoryProvider productRepository, ConfigurationRepositoryProvider configurationRepository) {
        return new ProductService(productRepository, configurationRepository);
    }

    @Bean
    public ConfigurationServiceProvider configurationService(ConfigurationRepositoryProvider configurationRepository) {
        return new ConfigurationService(configurationRepository);
    }

    @Bean
    public PageMapper pageMapper() {
        return new PageMapperImpl();
    }
}
