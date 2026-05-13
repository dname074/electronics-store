package pl.javakurs.dname074.product_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javakurs.dname074.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
