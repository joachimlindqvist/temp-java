package org.acme;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class GreeterService {
    @ConfigProperty(name = "whitespace.greeting.phrase")
    String greeting;

    void greet(String name) {
        System.out.println(greeting + name);
    }
}
