package pl.javakurs.dname074.order_service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import pl.javakurs.dname074.order.domain.KafkaSenderProvider;

@SpringBootTest
@Import(ContainerConfig.class)
class OrderServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@TestConfiguration
	static class TestConfig {
		@Bean
		KafkaSenderProvider kafkaSenderProvider() {
			return Mockito.mock(KafkaSenderProvider.class);
		}
	}

}
