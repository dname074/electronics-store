package pl.javakurs.dname074.order_service;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.javakurs.dname074.order.domain.CartClientProvider;
import pl.javakurs.dname074.order.domain.OrderRepositoryProvider;
import pl.javakurs.dname074.order.domain.OrderService;
import pl.javakurs.dname074.order.domain.OrderServiceProvider;

@Configuration
public class AppConfiguration {
    @Bean
    public OrderServiceProvider orderService(CartClientProvider cartClient,
                                             OrderRepositoryProvider orderRepository) {
        return new OrderService(cartClient, orderRepository);
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonNamingCustomizer() {
        return builder -> builder.propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }
}
