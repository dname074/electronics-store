package pl.javakurs.dname074.bff.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.javakurs.dname074.bff.model.PagePojo;
import pl.javakurs.dname074.bff.model.Product;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Slf4j
public class ProductService implements ProductServiceProvider {
    private final ProductClientProvider client;

    @Override
    public PagePojo<Product> getProductsPage(int page, int size, String type, BigDecimal minPrice, BigDecimal maxPrice) {
        log.info("Process of sending request to get a products page has started");
        return client.getProductsPage(page, size, type, minPrice, maxPrice);
    }

    @Override
    public Product getProduct(Long id) {
        log.info("Process of sending request to get a product has started");
        return client.getProduct(id);
    }
}
