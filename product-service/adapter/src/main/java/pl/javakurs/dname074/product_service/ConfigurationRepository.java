package pl.javakurs.dname074.product_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javakurs.dname074.product_service.entity.ConfigurationEntity;

@Repository
public interface ConfigurationRepository extends JpaRepository<ConfigurationEntity, Long> {
}
