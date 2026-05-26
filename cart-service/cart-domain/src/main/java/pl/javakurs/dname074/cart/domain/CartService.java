package pl.javakurs.dname074.cart.domain;

import lombok.RequiredArgsConstructor;
import pl.javakurs.dname074.cart.model.Cart;
import pl.javakurs.dname074.cart.model.CartProduct;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CartService implements CartServiceProvider {
    private final CartRepositoryProvider repository;
    private final ProductClientProvider client;

    @Override
    public Cart addToCart(String cartId, Long productId, List<Long> configurationIds) {
        Cart cart = (cartId != null)
                ? repository.findById(cartId).orElse(createNewCart())
                : createNewCart();
        CartProduct product = client.getProduct(productId);

//        product.getConfigurations().stream()
//                        .filter(configuration -> configurationIds.contains(configuration.getId()))
//
//        List<Configuration> configurations = new ArrayList<>();
//        if (configurationIds == null || configurationIds.isEmpty()) {
//            configurations = product.getConfigurations().stream()
//                    .filter(configuration -> configuration.getIsDefault()==true)
//                    .toList();
//        } else {
//            List<ConfigType> types = new ArrayList<>();
//            for (Configuration configuration : product.getConfigurations()) {
//                if (configurationIds.contains(configuration.getId())) {
//                    if (!types.contains(configuration.getType())) {
//                        configurations.add(configuration);
//                        types.add(configuration.getType());
//                    }
//                }
//            }
//        }

        cart.addProduct(product);
        repository.save(cart);
        return cart;
    }

    private Cart createNewCart() {
        return new Cart(UUID.randomUUID().toString(), new ArrayList<>(), BigDecimal.ZERO);
    }
}
