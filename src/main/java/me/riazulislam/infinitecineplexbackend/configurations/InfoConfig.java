package me.riazulislam.infinitecineplexbackend.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "info")
@Data
public class InfoConfig {
    private String githubLink;
    private String apiDocsLink;
}
