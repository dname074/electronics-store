package pl.javakurs.dname074.product_service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.javakurs.dname074.domain.ProductServiceProvider;
import pl.javakurs.dname074.model.PagePojo;
import pl.javakurs.dname074.model.Product;
import pl.javakurs.dname074.model.ProductType;

@Service
@RequiredArgsConstructor
public class ProductServiceFacade {
    private final ProductServiceProvider productService;

    public PagePojo<Product> getProductsPage(int page, int size, ProductType type) {
        return productService.getProductsPage(page, size, type);
    }

    public Product getProduct(Long id) {
        return productService.getProduct(id);
    }

    @Transactional
    public Product addProduct(Product product) {
        return productService.addProduct(product);
    }

    @Transactional
    public Product modifyProduct(Long id, Product product) {
        return productService.modifyProduct(id, product);
    }

    @Transactional
    public Product addConfigurationToProduct(Long productId, Long configurationId, Boolean isDefault) {
        return productService.addConfigurationToProduct(productId, configurationId, isDefault);
    }

    @Transactional
    public Product removeProduct(Long id) {
        return productService.removeProduct(id);
    }
}
