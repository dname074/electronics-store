package pl.javakurs.dname074.product_service;

import org.springframework.core.convert.converter.Converter;
import pl.javakurs.dname074.model.ProductType;

public class StringToProductTypeConverter implements Converter<String, ProductType> {
    @Override
    public ProductType convert(String source) {
        return ProductType.valueOf(source.toUpperCase());
    }
}
