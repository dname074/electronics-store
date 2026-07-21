package pl.javakurs.dname074.product_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductConfigurationId implements Serializable {
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "configuration_id")
    private Long configurationId;
}
