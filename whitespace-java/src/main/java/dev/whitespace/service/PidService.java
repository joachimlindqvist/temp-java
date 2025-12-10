package dev.whitespace.service;

import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PidService {

    @Inject
    private final RandomStringService randomStringService;

    String generate() {
        return randomStringService.alphanumerical(10);
    }
}
