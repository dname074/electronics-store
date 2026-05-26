package pl.javakurs.dname074.cart_service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import pl.javakurs.dname074.cart.model.Cart;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class CartRepository {
    private static final String CART_PREFIX = "cart:";
    private static final long TTL_HOURS = 24;

    private final RedisTemplate<String, Cart> redisTemplate;

    public void save(Cart cart) {
        String key = CART_PREFIX + cart.getId();
        redisTemplate.opsForValue().set(key, cart, TTL_HOURS, TimeUnit.HOURS);
    }

    public Optional<Cart> findById(String id) {
        String key = CART_PREFIX + id;
        Cart cart = redisTemplate.opsForValue().get(key);
        return Optional.ofNullable(cart);
    }

    public void deleteById(String cartId) {
        redisTemplate.delete(CART_PREFIX + cartId);
    }
}
