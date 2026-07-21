package pl.javakurs.dname074.order_service;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.javakurs.dname074.order.domain.*;

@Configuration
class AppConfiguration {
    @Bean
    public OrderServiceProvider orderService(CartClientProvider cartClient,
                                             OrderRepositoryProvider orderRepository,
                                             KafkaSenderProvider kafkaSender) {
        return new OrderService(cartClient, orderRepository, kafkaSender);
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonNamingCustomizer() {
        return builder -> builder.propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }
}
