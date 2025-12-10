package dev.whitespace.service;

import java.util.Random;

public class RandomStringService {
    final String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final String numbers = "0123456789";
    final String lettersNumbers = letters + numbers;

    String numerical(int len) {
        return from(numbers, len);
    }

    String alphabet(int len) {
        return from(letters, len);
    }

    String alphanumerical(int len) {
        return from(lettersNumbers, len);
    }

    String from(String dictionary, int len) {
        var r = new Random();
        var sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            sb.append(dictionary.charAt(r.nextInt(dictionary.length())));
        }

        return sb.toString();
    }
}
