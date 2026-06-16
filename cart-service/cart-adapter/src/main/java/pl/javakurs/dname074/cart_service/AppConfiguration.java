package pl.javakurs.dname074.cart_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import pl.javakurs.dname074.cart.domain.CartRepositoryProvider;
import pl.javakurs.dname074.cart.domain.CartService;
import pl.javakurs.dname074.cart.domain.CartServiceProvider;
import pl.javakurs.dname074.cart.domain.ConfigurationValidator;
import pl.javakurs.dname074.cart.domain.ProductClientProvider;
import pl.javakurs.dname074.cart.model.Cart;

@Configuration
class AppConfiguration {
    @Bean
    public CartRepositoryProvider cartRepositoryProvider(CartRepository repository) {
        return new CartRepositoryAdapter(repository);
    }

    @Bean
    public ProductClientProvider productClient(ProductClient client, ProductMapper mapper) {
        return new ProductClientAdapter(client, mapper);
    }

    @Bean
    public CartServiceProvider cartService(CartRepositoryProvider repository, ProductClientProvider productClient,
                                           ConfigurationValidator validator) {
        return new CartService(repository, productClient, validator);
    }

    @Bean
    public ConfigurationValidator configurationValidator() {
        return new ConfigurationValidator();
    }

    @Bean
    public RedisTemplate<String, Cart> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Cart> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        template.setKeySerializer(new StringRedisSerializer());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        Jackson2JsonRedisSerializer<Cart> serializer = new Jackson2JsonRedisSerializer<>(objectMapper, Cart.class);

        template.setValueSerializer(serializer);

        return template;
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonNamingCustomizer() {
        return builder -> builder.propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }
}
