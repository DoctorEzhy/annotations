package com.example.annotations.component;

import org.springframework.stereotype.Component;

@Component
public class TextUtils {

    public String capitalize(String text) {
        if (text == null || text.isBlank()) {
            return text;
        }
        return Character.toUpperCase(text.charAt(0)) + text.substring(1);
    }
}
