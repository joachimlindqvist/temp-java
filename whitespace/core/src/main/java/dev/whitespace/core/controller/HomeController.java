package dev.whitespace.core.controller;

import dev.whitespace.core.repository.KeyRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/")
public class HomeController {

    private final KeyRepository keyRepository;

    public HomeController(KeyRepository keyRepository) {
        this.keyRepository = keyRepository;
    }

    @GetMapping
    String Root() {
        var keys = keyRepository.findAll();
        return "this is root %d".formatted(keys.size());
    }
}
