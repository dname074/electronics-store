package pl.javakurs.dname074.cart.domain;

import pl.javakurs.dname074.cart.model.CartProduct;
import pl.javakurs.dname074.cart.model.ConfigType;
import pl.javakurs.dname074.cart.model.Configuration;
import pl.javakurs.dname074.cart.model.exception.DuplicateConfigurationException;
import pl.javakurs.dname074.cart.model.exception.InvalidConfigurationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ConfigurationValidator {
    public List<Configuration> getCorrectConfiguration(CartProduct product, List<Long> configurations) {
        if (configurations == null || configurations.isEmpty()) {
            return getDefaultConfiguration(product);
        }
        return getChosenConfigurations(product, configurations);
    }

    private List<Configuration> getDefaultConfiguration(CartProduct product) {
        List<Configuration> defaults = getProductConfigurations(product).stream()
                .filter(configuration -> Boolean.TRUE.equals(configuration.getIsDefault()))
                .toList();

        validateNoTypeDuplicates(defaults);

        return defaults;
    }

    private List<Configuration> getChosenConfigurations(CartProduct product, List<Long> configurations) {
        List<Configuration> validatedConfigs = getProductConfigurations(product).stream()
                .filter(configuration -> configurations.contains(configuration.getId()))
                .collect(Collectors.toList());
        validateProductHasConfigurations(validatedConfigs, configurations);
        validateNoTypeDuplicates(validatedConfigs);
        return fulfillListWithDefaultConfigurations(product, validatedConfigs);
    }

    private void validateProductHasConfigurations(List<Configuration> validatedConfigs, List<Long> requestedConfigs) {
        // sprawdzamy id ktore wspiera dany produkt
        Set<Long> validIds = validatedConfigs.stream()
                .map(Configuration::getId)
                .collect(Collectors.toSet());

        List<Long> unknownIds = requestedConfigs.stream()
                .filter(id -> !validIds.contains(id))
                .toList();

        if (!unknownIds.isEmpty()) {
            throw new InvalidConfigurationException(
                    "Unknown configuration IDs: " + unknownIds
            );
        }
    }

    private void validateNoTypeDuplicates(List<Configuration> configurations) {
        Map<ConfigType, Long> countByType = configurations.stream()
                .collect(Collectors.groupingBy(
                        Configuration::getType,
                        Collectors.counting()
                ));

        List<ConfigType> duplicates = countByType.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();

        if (!duplicates.isEmpty()) {
            throw new DuplicateConfigurationException("Duplicated configuration has been found");
        }
    }

    // add default configuration if type was not selected
    private List<Configuration> fulfillListWithDefaultConfigurations(CartProduct product, List<Configuration> validatedConfigs) {
        List<Configuration> fulfilledConfigurations = new ArrayList<>(validatedConfigs);
        List<Configuration> defaultConfigs = getDefaultConfiguration(product);

        Set<ConfigType> selectedTypes = validatedConfigs.stream()
                .map(Configuration::getType)
                .collect(Collectors.toSet());

        for (Configuration defaultConfig : defaultConfigs) {
            if (!selectedTypes.contains(defaultConfig.getType())) {
                fulfilledConfigurations.add(defaultConfig);
            }
        }
        return fulfilledConfigurations;
    }

    private List<Configuration> getProductConfigurations(CartProduct product) {
        if (product.getConfigurations() == null) {
            return List.of();
        }
        return product.getConfigurations();
    }
}
