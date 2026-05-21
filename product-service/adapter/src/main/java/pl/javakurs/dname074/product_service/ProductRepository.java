package pl.javakurs.dname074.product_service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.javakurs.dname074.product_service.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

@Repository
interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {
    Optional<ProductEntity> findBySku(String sku);

//    @Override
//    @EntityGraph(attributePaths = {
//            "configurations",
//            "configurations.configuration"
//    })
//    Page<ProductEntity> findAll(@Nullable Specification<ProductEntity> filters, Pageable pageable);

    @Query("SELECT p.id FROM ProductEntity p")
    Page<Long> findIds(Specification<ProductEntity> spec, Pageable pageable);

    @Query("""
            SELECT DISTINCT p FROM ProductEntity p
            LEFT JOIN FETCH p.configurations c
            LEFT JOIN FETCH c.configuration
            WHERE p.id IN :ids
            """)
    List<ProductEntity> findByIds(@Param("ids") List<Long> ids);
}
