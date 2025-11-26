package dev.whitespace.core.service.bundler.scanner;

import lombok.Setter;

import java.net.URI;

@FunctionalInterface
interface VisitorRelativeFn {
    String visit(String reference, String type);
}

@Setter
class RelativeVisitor {
    private URI base;

    String visit(String reference, String type) {
        if (base == null) {
            throw new RuntimeException("A location recordable must be recorded before any dependant recordables.");
        }

        try {
            return base.resolve(reference).toString();
        } catch (Exception e) {
            return base.toString();
        }
    }
}

@FunctionalInterface
interface VisitorFn {
    String visit(URI uri);
}
