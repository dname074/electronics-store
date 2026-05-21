package pl.javakurs.dname074.product_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Formula;
import pl.javakurs.dname074.model.ProductType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String sku;
    private String name;
    @Column(name = "base_price")
    private BigDecimal basePrice;
    @Enumerated(value = EnumType.STRING)
    private ProductType type;
    private String label;
    @BatchSize(size = 25)
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductConfigurationEntity> configurations;
    @Formula("""
    base_price + COALESCE((
        SELECT SUM(c.price)
        FROM product_configuration pc
        JOIN configurations c ON c.id = pc.configuration_id
        WHERE pc.product_id = id AND pc.is_default = true
    ), 0)
    """)
    private BigDecimal totalPrice;

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", basePrice=" + basePrice +
                ", type=" + type +
                ", label='" + label + '\'' +
                ", configurations=" + configurations +
                '}';
    }
}
