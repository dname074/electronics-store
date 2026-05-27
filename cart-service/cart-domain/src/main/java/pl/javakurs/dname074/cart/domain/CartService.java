package pl.javakurs.dname074.cart.domain;

import lombok.RequiredArgsConstructor;
import pl.javakurs.dname074.cart.model.Cart;
import pl.javakurs.dname074.cart.model.CartProduct;
import pl.javakurs.dname074.cart.model.Configuration;
import pl.javakurs.dname074.cart.model.exception.ResourceNotFoundException;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CartService implements CartServiceProvider {
    private final CartRepositoryProvider repository;
    private final ProductClientProvider client;
    private final ConfigurationValidator configValidator;

    @Override
    public Cart getCart(String id) {
        Cart cart = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart with provided id not found"));
        return cart;
    }

    @Override
    public Cart addToCart(String cartId, Long productId, List<Long> configurationIds) {
        Cart cart = (cartId != null)
                ? repository.findById(cartId).orElse(createNewCart())
                : createNewCart();
        CartProduct product = client.getProduct(productId);
        List<Configuration> configs = configValidator.getCorrectConfiguration(product, configurationIds);

        product.setConfigurations(configs);
        cart.addProduct(product);
        cart.calculateTotalPrice();
        repository.save(cart);
        return cart;
    }

    private Cart createNewCart() {
        return new Cart(UUID.randomUUID().toString(), new ArrayList<>(), BigDecimal.ZERO);
    }
}
