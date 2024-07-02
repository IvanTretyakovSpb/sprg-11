package com.tretyakov.aspect;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TimerProperties.class)
@ConditionalOnProperty(value = "aspect.enabled", havingValue = "true")
public class AspectTimerAutoConfiguration {

    @Bean
    public TimerAspect timerAspect(TimerProperties properties) {
        return new TimerAspect(properties);
    }

}
