package pl.javakurs.dname074.domain;

import pl.javakurs.dname074.model.PagePojo;
import pl.javakurs.dname074.model.Product;
import pl.javakurs.dname074.model.ProductType;

public interface ProductServiceProvider {
    PagePojo<Product> getProductsPage(int page, int size, ProductType type);

    Product getProduct(Long id);

    Product addProduct(Product product);

    Product modifyProduct(Long id, Product product);

    Product addConfigurationToProduct(Long productId, Long configurationId, Boolean isDefault);

    Product removeProduct(Long id);
}
