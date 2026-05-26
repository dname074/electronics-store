package pl.javakurs.dname074.cart_service;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import pl.javakurs.dname074.cart.domain.CartRepositoryProvider;
import pl.javakurs.dname074.cart.domain.CartService;
import pl.javakurs.dname074.cart.domain.CartServiceProvider;
import pl.javakurs.dname074.cart.domain.ProductClientProvider;
import pl.javakurs.dname074.cart.model.Cart;

@Configuration
public class AppConfiguration {
    @Bean
    public CartRepositoryProvider cartRepositoryProvider(CartRepository repository) {
        return new CartRepositoryAdapter(repository);
    }

    @Bean
    public ProductClientProvider productClient(ProductClient client, ProductMapper mapper) {
        return new ProductClientAdapter(client, mapper);
    }

    @Bean
    public CartServiceProvider cartService(CartRepositoryProvider repository, ProductClientProvider productClient) {
        return new CartService(repository, productClient);
    }

    @Bean
    public RedisTemplate<String, Cart> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Cart> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return template;
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonNamingCustomizer() {
        return builder -> builder.propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }
}
