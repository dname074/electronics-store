package pl.javakurs.dname074.domain;

import lombok.RequiredArgsConstructor;
import org.mockito.ArgumentMatcher;
import pl.javakurs.dname074.model.Configuration;

import java.util.Objects;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class ConfigurationArgumentMatcher implements ArgumentMatcher<Configuration> {
    private final Configuration configuration;

    @Override
    public boolean matches(Configuration configuration) {
        return nonNull(configuration) &&
                this.configuration.getId().equals(configuration.getId()) &&
                this.configuration.getName().equals(configuration.getName()) &&
                this.configuration.getType().equals(configuration.getType()) &&
                this.configuration.getPrice().equals(configuration.getPrice()) &&
                this.configuration.getLabel().equals(configuration.getLabel()) &&
                (Objects.equals(this.configuration.getProduct(), configuration.getProduct()) ||
                (Objects.equals(this.configuration.getProduct(), configuration.getProduct()) &&
                        this.configuration.getProduct().getSku().equals(configuration.getProduct().getSku())
                ));
    }
}
