package pl.javakurs.dname074.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductConfiguration {
    private Product product;
    private Configuration configuration;
    private Boolean isDefault;
}
