package org.acme.bundler;

public class Bundler {
    private Input input;

    Bundler(Input input) {
        this.input = input;
    }

    public static Bundler of(Input input) {
        return new Bundler(input);
    }
}
