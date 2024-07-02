package ru.tretyakov.http.logging;

import lombok.Data;
import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("http.logging")
public class LoggingProperties {

    /**
     * Включение логирования запроса
     */
    private boolean logBody = false;

    /**
     * Уровень логирования
     */
    private Level logLevel = Level.WARN;
}
