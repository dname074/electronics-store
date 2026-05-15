package pl.javakurs.dname074.domain;

import lombok.RequiredArgsConstructor;
import pl.javakurs.dname074.model.PagePojo;
import pl.javakurs.dname074.model.Product;

@RequiredArgsConstructor
public class ProductService implements ProductServiceProvider {
    private final ProductRepositoryProvider repository;

    @Override
    public PagePojo<Product> getProductsPage(int page, int size) {
        return repository.findAll(page, size);
    }

    @Override
    public Product getProduct(Long id) {
        return repository.findById(id);
    }
}
