package dev.whitespace.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public class PidService {

    @Autowired
    private final RandomStringService randomStringService;

    String generate() {
        return randomStringService.alphanumerical(10);
    }
}
