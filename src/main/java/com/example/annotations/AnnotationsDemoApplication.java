package com.example.annotations;

import com.example.annotations.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@EnableAutoConfiguration
@Import(AppConfig.class)
public class AnnotationsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnnotationsDemoApplication.class, args);
    }
}
