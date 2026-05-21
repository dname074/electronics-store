package pl.javakurs.dname074.product_service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import pl.javakurs.dname074.domain.ProductRepositoryProvider;
import pl.javakurs.dname074.model.PagePojo;
import pl.javakurs.dname074.model.Product;
import pl.javakurs.dname074.model.ProductType;
import pl.javakurs.dname074.product_service.entity.ProductConfigurationId;
import pl.javakurs.dname074.product_service.entity.ProductEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class ProductRepositoryAdapter implements ProductRepositoryProvider {
    private final ProductRepository repository;
    private final ProductMapper productMapper;
    private final PageMapper pageMapper;

    @Override
    public PagePojo<Product> findAll(int page, int size, ProductType type, BigDecimal minPrice, BigDecimal maxPrice) {
        Specification<ProductEntity> filters = setFilters(type, minPrice, maxPrice);
        Pageable pageable = PageRequest.of(page, size);

        Page<Long> idPage = repository.findIds(filters, pageable);
        List<ProductEntity> products = repository.findByIds(idPage.getContent());
        Page<ProductEntity> productPage = new PageImpl<>(products, pageable, idPage.getTotalElements());

        return pageMapper.entityToPojo(productPage, productMapper::entityToPojo);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id)
                .map(productMapper::entityToPojo);
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        return repository.findBySku(sku)
                .map(productMapper::entityToPojo);
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = productMapper.pojoToEntity(product);
        if (productEntity.getConfigurations() != null) {
            productEntity.getConfigurations()
                    .forEach(pce -> {
                        pce.setProduct(productEntity);
                        pce.setProductConfigurationId(new ProductConfigurationId(
                                productEntity.getId(),
                                pce.getConfiguration().getId()
                        ));
                    });
        }
        return productMapper.entityToPojo(repository.save(productEntity));
    }

    @Override
    public Product delete(Product product) {
        repository.deleteById(product.getId());
        return product;
    }

    private Specification<ProductEntity> setFilters(ProductType type, BigDecimal minPrice, BigDecimal maxPrice) {
        Specification<ProductEntity> filters = Specification.allOf();
        if (type != null) {
            filters = filters.and(ProductSpecifications.hasType(type));
        }
        if (minPrice != null) {
            filters = filters.and(ProductSpecifications.hasMinPrice(minPrice));
        }
        if (maxPrice != null) {
            filters = filters.and(ProductSpecifications.hasMaxPrice(maxPrice));
        }
        return filters;
    }
}
