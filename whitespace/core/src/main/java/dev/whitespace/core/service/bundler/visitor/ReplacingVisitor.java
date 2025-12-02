package dev.whitespace.core.service.bundler.visitor;

import dev.whitespace.core.service.bundler.domain.Reference;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.HashMap;
import java.util.Optional;

@RequiredArgsConstructor
public class ReplacingVisitor implements AbsoluteVisitor {
	private final HashMap<URI, Reference> references;

	@Override
	public String visit(Reference reference) {
		return Optional.ofNullable(references.get(reference.url()))
				.map(r -> r.toString())
				.orElse(reference.toString());
	}
}
