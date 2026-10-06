package com.example.annotations.runner;

import com.example.annotations.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Component
public class BeanInfoRunner implements CommandLineRunner {

    private static final String BASE_PACKAGE = "com.example.annotations";

    private final ApplicationContext context;
    private final UserService userService;

    @Autowired
    public BeanInfoRunner(ApplicationContext context, UserService userService) {
        this.context = context;
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        System.out.println("=== Бины приложения (пакет " + BASE_PACKAGE + ") ===");

        Arrays.stream(context.getBeanDefinitionNames())
                .sorted()
                .filter(this::isOurBean)
                .forEach(name -> System.out.printf("%-28s | %-14s | %s%n",
                        name, detectKind(name), context.getType(name).getSimpleName()));

        System.out.println("Всего бинов в контексте: " + context.getBeanDefinitionCount());

        System.out.println("\n=== Результат работы UserService ===");
        userService.greetAll().forEach(System.out::println);
    }

    private boolean isOurBean(String name) {
        Class<?> type = context.getType(name);
        return type != null && type.getName().startsWith(BASE_PACKAGE);
    }

    private String detectKind(String name) {
        if (context.findAnnotationOnBean(name, Repository.class) != null) return "@Repository";
        if (context.findAnnotationOnBean(name, Service.class) != null) return "@Service";
        if (context.findAnnotationOnBean(name, Configuration.class) != null) return "@Configuration";
        if (context.findAnnotationOnBean(name, Component.class) != null) return "@Component";
        return "@Bean";
    }
}
