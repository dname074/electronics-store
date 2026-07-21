package pl.javakurs.dname074.bff.domain;

import pl.javakurs.dname074.bff.model.PagePojo;
import pl.javakurs.dname074.bff.model.Product;

import java.math.BigDecimal;

public interface ProductClientProvider {
    PagePojo<Product> getProductsPage(int page, int size, String type, BigDecimal minPrice, BigDecimal maxPrice);

    Product getProduct(Long id);
}
