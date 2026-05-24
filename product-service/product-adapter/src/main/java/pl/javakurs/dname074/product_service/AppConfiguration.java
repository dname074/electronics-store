package pl.javakurs.dname074.product_service;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
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
    public ProductServiceProvider productService(ProductRepositoryProvider productRepository,
                                                 ConfigurationRepositoryProvider configurationRepository) {
        return new ProductService(productRepository, configurationRepository);
    }

    @Bean
    public ConfigurationServiceProvider configurationService(ConfigurationRepositoryProvider configurationRepository,
                                                             ProductRepositoryProvider productRepository) {
        return new ConfigurationService(configurationRepository, productRepository);
    }

    @Bean
    public PageMapper pageMapper() {
        return new PageMapperImpl();
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonFeaturesCustomizer() {
        return builder -> builder.featuresToEnable(
                MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS
        );
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonNamingCustomizer() {
        return builder -> builder.propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }
}
