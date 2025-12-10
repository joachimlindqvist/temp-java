package dev.whitespace.service.bundler.visitor;

import dev.whitespace.service.bundler.domain.Reference;
import lombok.Getter;

import java.net.URI;
import java.util.HashMap;

@Getter
public class FindingVisitor implements AbsoluteVisitor {
	private HashMap<URI, Reference> references = new HashMap<>();

	@Override
	public String visit(Reference reference) {
		references.put(reference.url(), reference);
		return null;
	}
}
