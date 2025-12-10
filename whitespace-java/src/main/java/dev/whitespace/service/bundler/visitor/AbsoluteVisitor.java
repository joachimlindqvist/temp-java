package dev.whitespace.service.bundler.visitor;

import dev.whitespace.service.bundler.domain.Reference;

public interface AbsoluteVisitor {
	String visit(Reference reference);
}
