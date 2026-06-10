package pl.javakurs.dname074.cart_service;

import com.redis.testcontainers.RedisContainer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;

@TestConfiguration(proxyBeanMethods = false)
public class ContainerConfig {
    @Bean
    @ServiceConnection
    RedisContainer redis() {
        return new RedisContainer("redis:7.4-alpine");
    }
}
