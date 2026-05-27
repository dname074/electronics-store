package pl.javakurs.dname074.cart.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.javakurs.dname074.cart.model.Cart;
import pl.javakurs.dname074.cart.model.CartProduct;
import pl.javakurs.dname074.cart.model.Configuration;
import pl.javakurs.dname074.cart.model.exception.ResourceNotFoundException;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class CartService implements CartServiceProvider {
    private final CartRepositoryProvider repository;
    private final ProductClientProvider client;
    private final ConfigurationValidator configValidator;

    @Override
    public Cart getCart(String id) {
        log.info("Process of getting cart has started");
        Cart cart = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart with provided id not found"));
        log.info("Process of getting cart has ended");
        return cart;
    }

    @Override
    public Cart addToCart(String cartId, Long productId, List<Long> configurationIds) {
        log.info("Process of adding product to cart has started");
        Cart cart = (cartId != null)
                ? repository.findById(cartId).orElse(createNewCart())
                : createNewCart();
        CartProduct product = client.getProduct(productId);
        List<Configuration> configs = configValidator.getCorrectConfiguration(product, configurationIds);

        product.setConfigurations(configs);
        cart.addProduct(product);
        cart.calculateTotalPrice();
        repository.save(cart);
        log.info("Process of adding product to cart has ended");
        return cart;
    }

    private Cart createNewCart() {
        return new Cart(UUID.randomUUID().toString(), new ArrayList<>(), BigDecimal.ZERO);
    }
}
