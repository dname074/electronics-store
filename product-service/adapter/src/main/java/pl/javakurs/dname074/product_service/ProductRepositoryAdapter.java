package pl.javakurs.dname074.product_service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import pl.javakurs.dname074.domain.ProductRepositoryProvider;
import pl.javakurs.dname074.model.PagePojo;
import pl.javakurs.dname074.model.Product;

@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepositoryProvider {
    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final PageMapper pageMapper;

    @Override
    public PagePojo<Product> findAll(int page, int size) {
        PagePojo<Product> productPage = pageMapper.entityToPojo(
                repository.findAll(PageRequest.of(page, size)),
                mapper::entityToPojo
        );
        return productPage;
    }

    @Override
    public Product findById(Long id) {
        return mapper.entityToPojo(repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found")));
    }
}
