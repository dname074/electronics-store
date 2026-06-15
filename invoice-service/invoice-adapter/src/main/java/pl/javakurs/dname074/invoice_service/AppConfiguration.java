package pl.javakurs.dname074.invoice_service;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.javakurs.dname074.invoice.domain.InvoiceClientProvider;
import pl.javakurs.dname074.invoice.domain.InvoiceRepositoryProvider;
import pl.javakurs.dname074.invoice.domain.InvoiceService;
import pl.javakurs.dname074.invoice.domain.InvoiceServiceProvider;

@Configuration
public class AppConfiguration {
    @Bean
    public InvoiceServiceProvider invoiceService(InvoiceClientProvider client, InvoiceRepositoryProvider repository,
                                                 ServiceProperties properties) {
        return new InvoiceService(properties.getPaymentDays(), properties.getDefaultTax(), properties.getKind(),
                properties.getCurrency(), properties.getSellerName(), client, repository);
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonNamingCustomizer() {
        return builder -> builder.propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }
}
