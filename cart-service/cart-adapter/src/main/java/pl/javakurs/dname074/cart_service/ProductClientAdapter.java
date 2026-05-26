package pl.javakurs.dname074.cart_service;

import lombok.RequiredArgsConstructor;
import pl.javakurs.dname074.cart.domain.ProductClientProvider;
import pl.javakurs.dname074.cart.model.CartProduct;

@RequiredArgsConstructor
public class ProductClientAdapter implements ProductClientProvider {
    private final ProductClient client;
    private final ProductMapper mapper;

    @Override
    public CartProduct getProduct(Long id) {
        return mapper.toPojo(client.getProduct(id));
    }
}
