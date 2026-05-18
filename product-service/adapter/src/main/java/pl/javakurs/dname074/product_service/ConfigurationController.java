package pl.javakurs.dname074.product_service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.javakurs.dname074.domain.ConfigurationServiceProvider;
import pl.javakurs.dname074.dto.ConfigurationDto;
import pl.javakurs.dname074.dto.CreateConfigurationCommand;
import pl.javakurs.dname074.model.Configuration;

@RestController
@RequiredArgsConstructor
@RequestMapping("/configurations")
public class ConfigurationController {
    private final ConfigurationServiceProvider configurationService;
    private final ConfigurationMapper configurationMapper;

    @PostMapping
    public ConfigurationDto addConfiguration(@RequestBody CreateConfigurationCommand configuration) {
        Configuration config = configurationService.addConfiguration(configurationMapper.dtoToPojo(configuration), configuration.productId());
        return configurationMapper.toDto(config);
    }
}
