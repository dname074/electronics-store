package pl.javakurs.dname074.cart_service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import pl.javakurs.dname074.cart.domain.CartRepositoryProvider;
import pl.javakurs.dname074.cart.domain.CartService;
import pl.javakurs.dname074.cart.domain.CartServiceProvider;
import pl.javakurs.dname074.cart.model.Cart;

import java.time.Duration;

@Configuration
public class AppConfiguration {
    @Bean
    public CartRepositoryProvider cartRepository(CartRepository cartRepository) {
        return new CartRepositoryAdapter(cartRepository);
    }

    @Bean
    public ProductClientProvider productClient() {
        return new ProductClient();
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
}
