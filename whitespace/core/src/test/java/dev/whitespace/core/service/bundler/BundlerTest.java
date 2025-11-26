package dev.whitespace.core.service.bundler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.whitespace.core.service.bundler.domain.Recordable;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class BundlerTest {
    @Test
    void Test() throws IOException, URISyntaxException {
        var objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        var path = Path.of(Objects.requireNonNull(BundlerTest.class.getResource("/fixtures/short_recordables")).toURI());
        var lines = Files.readAllLines(path, StandardCharsets.UTF_8).stream()
                .map(line -> {
                    try {
                        return objectMapper.readValue(line, Recordable.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();

        IO.println(lines);
    }
}
