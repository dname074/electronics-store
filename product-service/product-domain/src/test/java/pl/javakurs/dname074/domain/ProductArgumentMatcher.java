package pl.javakurs.dname074.domain;

import lombok.RequiredArgsConstructor;
import org.mockito.ArgumentMatcher;
import pl.javakurs.dname074.model.Product;

import java.util.Objects;

@RequiredArgsConstructor
public class ProductArgumentMatcher implements ArgumentMatcher<Product> {
    private final Product product;

    @Override
    public boolean matches(Product product) {
        return Objects.nonNull(product) &&
                this.product.getId().equals(product.getId()) &&
                this.product.getSku().equals(product.getSku()) &&
                this.product.getName().equals(product.getName()) &&
                this.product.getBasePrice().equals(product.getBasePrice()) &&
                this.product.getType().equals(product.getType()) &&
                this.product.getLabel().equals(product.getLabel());
    }
}
