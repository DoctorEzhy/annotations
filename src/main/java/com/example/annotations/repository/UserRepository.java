package com.example.annotations.repository;

import com.example.annotations.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {

    private final Map<Integer, User> storage = new ConcurrentHashMap<>();

    public UserRepository() {
        storage.put(1, new User(1, "alice"));
        storage.put(2, new User(2, "bob"));
    }

    public Optional<User> findById(int id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<User> findAll() {
        return List.copyOf(storage.values());
    }
}
