package com.example.annotations.service;

import com.example.annotations.component.GreetingFormatter;
import com.example.annotations.component.TextUtils;
import com.example.annotations.model.User;
import com.example.annotations.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Service - стереотип слоя бизнес-логики. Технически работает как @Component,
 * но показывает роль класса: он не работает с хранилищем напрямую,
 * а использует репозиторий и содержит логику приложения.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final GreetingFormatter greetingFormatter;
    private final TextUtils textUtils;

    /**
     * @Autowired на конструкторе - внедрение зависимостей через конструктор (рекомендуемый способ):
     * поля final, зависимости обязательны, класс легко тестировать.
     * Если в классе один конструктор, @Autowired можно опустить, но здесь он указан явно по заданию.
     */
    @Autowired
    public UserService(UserRepository userRepository,
                       GreetingFormatter greetingFormatter,
                       TextUtils textUtils) {
        this.userRepository = userRepository;
        this.greetingFormatter = greetingFormatter;
        this.textUtils = textUtils;
    }

    /** Бизнес-логика: получить приветствия для всех пользователей. */
    public List<String> greetAll() {
        return userRepository.findAll().stream()
                .sorted((a, b) -> Integer.compare(a.id(), b.id()))
                .map(User::name)
                .map(textUtils::capitalize)
                .map(greetingFormatter::format)
                .toList();
    }
}
