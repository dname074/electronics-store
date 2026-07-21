package pl.javakurs.dname074.domain;

import pl.javakurs.dname074.model.PagePojo;
import pl.javakurs.dname074.model.Product;
import pl.javakurs.dname074.model.ProductType;

import java.math.BigDecimal;

public interface ProductServiceProvider {
    PagePojo<Product> getProductsPage(int page, int size, ProductType type, BigDecimal minPrice, BigDecimal maxPrice);

    Product getProduct(Long id);

    Product addProduct(Product product);

    Product modifyProduct(Long id, Product product);

    Product addConfigurationToProduct(Long productId, Long configurationId, Boolean isDefault);

    Product removeProduct(Long id);
}
