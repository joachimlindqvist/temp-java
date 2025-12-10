package dev.whitespace.service.bundler.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CssValue {
    private String value;

    public CssValue(String value) {
        this.value = value;
    }
}
