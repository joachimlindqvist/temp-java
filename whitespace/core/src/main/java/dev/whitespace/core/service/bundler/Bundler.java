package dev.whitespace.core.service.bundler;

import java.util.List;

import dev.whitespace.core.service.bundler.domain.Recordable;
import dev.whitespace.core.service.bundler.resolver.ReferenceResolver;
import dev.whitespace.core.service.bundler.scanner.RecordableScanner;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Bundler {
	private final List<Recordable> recordables;

	private final ReferenceResolver referenceResolver = new ReferenceResolver();

    void execute() {
		var refs = new RecordableScanner(recordables).find();
		var result = referenceResolver.resolve(refs);
		IO.println(result);
    }
}
