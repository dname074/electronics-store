package pl.javakurs.dname074.cart.domain;

import pl.javakurs.dname074.cart.model.CartProduct;

public interface ProductClientProvider {
    CartProduct getProduct(Long id);
}
