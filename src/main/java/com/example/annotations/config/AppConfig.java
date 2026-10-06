package com.example.annotations.config;

import com.example.annotations.component.GreetingFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.annotations")
public class AppConfig {

    @Bean
    public AppInfo appInfo() {
        return new AppInfo("Annotations Demo", "1.0.0");
    }

    @Bean
    public GreetingFormatter greetingFormatter(AppInfo appInfo) {
        return new GreetingFormatter(appInfo.name());
    }

    public record AppInfo(String name, String version) {
    }
}
