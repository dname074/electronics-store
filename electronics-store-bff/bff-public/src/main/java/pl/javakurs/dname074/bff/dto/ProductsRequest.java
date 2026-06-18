package pl.javakurs.dname074.bff.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductsRequest {
    private Integer page = 0;
    private Integer size = 10;
    private String type;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}
