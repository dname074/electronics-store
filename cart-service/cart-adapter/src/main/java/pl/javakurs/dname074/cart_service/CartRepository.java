package pl.javakurs.dname074.cart_service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import pl.javakurs.dname074.cart.model.Cart;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "cart")
class CartRepository {
    private final ConfigProperties properties;

    private final RedisTemplate<String, Cart> redisTemplate;

    public void save(Cart cart) {
        String key = properties.getCartPrefix() + cart.getId();
        redisTemplate.opsForValue().set(key, cart, properties.getTtlHours(), TimeUnit.HOURS);
    }

    public Optional<Cart> findById(String id) {
        String key = properties.getCartPrefix() + id;
        Cart cart = redisTemplate.opsForValue().get(key);
        return Optional.ofNullable(cart);
    }

    public void deleteById(String cartId) {
        redisTemplate.delete(properties.getCartPrefix() + cartId);
    }
}
