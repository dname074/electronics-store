package pl.javakurs.dname074.cart_service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cart")
@Getter
@Setter
public class ConfigProperties {
    private String cartPrefix;
    private long ttlHours;
}
