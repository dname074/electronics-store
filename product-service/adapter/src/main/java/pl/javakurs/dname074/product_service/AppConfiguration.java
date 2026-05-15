package pl.javakurs.dname074.product_service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.javakurs.dname074.domain.ProductRepositoryProvider;
import pl.javakurs.dname074.domain.ProductService;
import pl.javakurs.dname074.domain.ProductServiceProvider;

@Configuration
public class AppConfiguration {
    @Bean
    public ProductServiceProvider productService(ProductRepositoryProvider repository) {
        return new ProductService(repository);
    }

    @Bean
    public PageMapper pageMapper() {
        return new PageMapperImpl();
    }
}
