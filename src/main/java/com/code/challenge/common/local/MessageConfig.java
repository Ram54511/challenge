package com.code.challenge.common.local;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
/**
 * @author Ram Thapa
 */
@Configuration
public class MessageConfig {

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("local/messages");
        source.setDefaultEncoding("UTF-8");
        return source;
    }
}
