package pl.javakurs.dname074.product_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product_configuration")
public class ProductConfigurationEntity {
    @EmbeddedId
    private ProductConfigurationId productConfigurationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("configurationId")
    @JoinColumn(name = "configuration_id")
    private ConfigurationEntity configuration;

    @Column(name = "is_default")
    private Boolean isDefault;
}
