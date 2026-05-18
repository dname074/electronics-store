package pl.javakurs.dname074.product_service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import pl.javakurs.dname074.model.ProductType;
import pl.javakurs.dname074.product_service.entity.ProductEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductSpecifications {
    public static Specification<ProductEntity> hasType(ProductType type) {
        return (root, query, cb) ->
                cb.equal(root.get("type"), type);
    }
}
