package com.nirajtailor.springcloudconfigclient.Config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.config")
@EnableConfigurationProperties
@Data
public class AppConfig {
    String configA;
    String configB;
    String configC;
    String configD;

}
