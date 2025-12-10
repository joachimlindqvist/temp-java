package dev.whitespace.service.bundler.visitor;

import dev.whitespace.service.bundler.domain.Reference;
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
				.map(Reference::toString)
				.orElse(reference.toString());
	}
}
