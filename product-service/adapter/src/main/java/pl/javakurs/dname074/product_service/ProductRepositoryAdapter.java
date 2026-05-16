package pl.javakurs.dname074.product_service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import pl.javakurs.dname074.domain.ProductRepositoryProvider;
import pl.javakurs.dname074.model.PagePojo;
import pl.javakurs.dname074.model.Product;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepositoryProvider {
    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final PageMapper pageMapper;

    @Override
    public PagePojo<Product> findAll(int page, int size) {
        return pageMapper.entityToPojo(
                repository.findAll(PageRequest.of(page, size)),
                mapper::entityToPojo
        );
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id)
                .map(mapper::entityToPojo);
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        return repository.findBySku(sku)
                .map(mapper::entityToPojo);
    }

    @Override
    public Product save(Product product) {
        return mapper.entityToPojo(repository.save(mapper.pojoToEntity(product)));
    }
}
