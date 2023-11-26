package io.dongvelop.actuatordemo.custom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyLibraryEndpointConfig {

    @Bean
    public MyLibraryInfoEndpoint myLibraryInfoEndpoint() {
        return new MyLibraryInfoEndpoint();
    }
}
