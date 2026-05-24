package pl.javakurs.dname074.product_service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import pl.javakurs.dname074.model.ProductType;
import pl.javakurs.dname074.product_service.entity.ProductEntity;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ProductSpecifications {
    public static Specification<ProductEntity> hasType(ProductType type) {
        return (root, query, cb) ->
                cb.equal(root.get("type"), type);
    }

    public static Specification<ProductEntity> hasMinPrice(BigDecimal minPrice) {
        return (root, query, cb) ->
                cb.greaterThanOrEqualTo(root.get("totalPrice"), minPrice);
    }

    public static Specification<ProductEntity> hasMaxPrice(BigDecimal maxPrice) {
        return (root, query, cb) ->
                cb.lessThanOrEqualTo(root.get("totalPrice"), maxPrice);
    }
}
