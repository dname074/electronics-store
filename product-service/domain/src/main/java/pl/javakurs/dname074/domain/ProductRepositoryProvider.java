package pl.javakurs.dname074.domain;

import pl.javakurs.dname074.model.PagePojo;
import pl.javakurs.dname074.model.Product;
import pl.javakurs.dname074.model.ProductType;

import java.util.Optional;

public interface ProductRepositoryProvider {
    PagePojo<Product> findAll(int page, int size, ProductType type);
    Optional<Product> findById(Long id);
    Optional<Product> findBySku(String sku);
    Product save(Product product);
    Product delete(Product product);
}
