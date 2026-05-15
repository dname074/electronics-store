package pl.javakurs.dname074.domain;

import pl.javakurs.dname074.model.PagePojo;
import pl.javakurs.dname074.model.Product;

public interface ProductRepositoryProvider {
    PagePojo<Product> findAll(int page, int size);
    Product findById(Long id);
}
