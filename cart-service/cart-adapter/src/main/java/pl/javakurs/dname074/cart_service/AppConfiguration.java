package pl.javakurs.dname074.cart_service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.javakurs.dname074.cart.domain.CartService;
import pl.javakurs.dname074.cart.domain.CartServiceProvider;

@Configuration
public class AppConfiguration {
    @Bean
    public CartServiceProvider cartService() {
        return new CartService();
    }
}
