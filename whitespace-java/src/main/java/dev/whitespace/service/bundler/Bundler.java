package dev.whitespace.service.bundler;

import dev.whitespace.service.bundler.domain.Recordable;
import dev.whitespace.service.bundler.resolver.ReferenceResolver;
import dev.whitespace.service.bundler.scanner.RecordableScanner;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
