package pl.javakurs.dname074.product_service;

import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import pl.javakurs.dname074.product_service.entity.ProductEntity;

import java.util.Optional;

@Repository
interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {
    Optional<ProductEntity> findBySku(String sku);

    @Override
    Page<ProductEntity> findAll(@Nullable Specification<ProductEntity> filters, Pageable pageable);
}
