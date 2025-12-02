package dev.whitespace.core.service.bundler.visitor;

import java.net.URI;
import java.util.HashMap;

import dev.whitespace.core.service.bundler.domain.Reference;
import lombok.Getter;

@Getter
public class FindingVisitor implements AbsoluteVisitor {
	private HashMap<URI, Reference> references = new HashMap<>();

	@Override
	public String visit(Reference reference) {
		references.put(reference.url(), reference);
		return null;
	}
}
