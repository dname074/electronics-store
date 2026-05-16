package pl.javakurs.dname074.product_service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.javakurs.dname074.domain.ConfigurationServiceProvider;
import pl.javakurs.dname074.dto.ConfigurationDto;
import pl.javakurs.dname074.dto.CreateConfigurationCommand;

@RestController
@RequiredArgsConstructor
@RequestMapping("/configurations")
public class ConfigurationController {
    private final ConfigurationServiceProvider configurationService;
    private final ConfigurationMapper configurationMapper;

    @PostMapping
    public ConfigurationDto addConfiguration(CreateConfigurationCommand configuration) {
        return configurationMapper.toDto(configurationService.addConfiguration(configurationMapper.dtoToPojo(configuration)));
    }
}
