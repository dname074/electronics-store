package pl.javakurs.dname074.electronics_store_bff;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.javakurs.dname074.bff.domain.ProductClientProvider;
import pl.javakurs.dname074.bff.model.PagePojo;
import pl.javakurs.dname074.bff.model.Product;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ProductClientAdapter implements ProductClientProvider {
    private final ProductClient client;
    private final PageMapper pageMapper;
    private final ProductMapper productMapper;

    @Override
    public PagePojo<Product> getProductsPage(int page, int size, String type, BigDecimal minPrice, BigDecimal maxPrice) {
        return pageMapper.dtoToPojo(client.getProductsPage(page, size, type, minPrice, maxPrice), productMapper::toPojo);
    }

    @Override
    public Product getProduct(Long id) {
        return productMapper.toPojo(client.getProduct(id));
    }
}
