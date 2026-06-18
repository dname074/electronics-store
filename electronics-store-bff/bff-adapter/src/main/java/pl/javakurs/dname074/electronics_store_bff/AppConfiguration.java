package pl.javakurs.dname074.electronics_store_bff;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.javakurs.dname074.bff.domain.CartClientProvider;
import pl.javakurs.dname074.bff.domain.CartService;
import pl.javakurs.dname074.bff.domain.CartServiceProvider;
import pl.javakurs.dname074.bff.domain.OrderClientProvider;
import pl.javakurs.dname074.bff.domain.OrderService;
import pl.javakurs.dname074.bff.domain.OrderServiceProvider;
import pl.javakurs.dname074.bff.domain.ProductClientProvider;
import pl.javakurs.dname074.bff.domain.ProductService;
import pl.javakurs.dname074.bff.domain.ProductServiceProvider;

@Configuration
public class AppConfiguration {
    @Bean
    public OrderServiceProvider orderService(OrderClientProvider orderClient) {
        return new OrderService(orderClient);
    }

    @Bean
    public CartServiceProvider cartService(CartClientProvider cartClient) {
        return new CartService(cartClient);
    }

    @Bean
    public ProductServiceProvider productService(ProductClientProvider productClient) {
        return new ProductService(productClient);
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
