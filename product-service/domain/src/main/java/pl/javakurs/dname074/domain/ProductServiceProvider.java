package pl.javakurs.dname074.domain;

import pl.javakurs.dname074.model.PagePojo;
import pl.javakurs.dname074.model.Product;

public interface ProductServiceProvider {
    PagePojo<Product> getProductsPage(int page, int size);

    Product getProduct(Long id);
}
