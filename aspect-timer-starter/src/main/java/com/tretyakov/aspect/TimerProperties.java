package com.tretyakov.aspect;

import lombok.Data;
import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("aspect")
public class TimerProperties {

    /**
     * Включение логирования таймера исполнения методов
     */
    private boolean onTimer = false;

    /**
     * Уровень логирования
     */
    private Level logLevel = Level.WARN;
}
