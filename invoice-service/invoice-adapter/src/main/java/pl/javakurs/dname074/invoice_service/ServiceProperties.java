package pl.javakurs.dname074.invoice_service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "invoice")
public class ServiceProperties {
    private Integer paymentDays;
    private Integer defaultTax;
    private String kind;
    private String currency;
    private String sellerName;
}
