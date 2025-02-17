package ru.tretyakov.http.logging;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LoggingProperties.class)
@ConditionalOnProperty(value = "http.logging.enabled", havingValue = "true")
public class HttpLoggingAutoConfiguration {

    @Bean
    public LoggingFilter loggingFilter(LoggingProperties properties) {
        return new LoggingFilter(properties);
    }
}
