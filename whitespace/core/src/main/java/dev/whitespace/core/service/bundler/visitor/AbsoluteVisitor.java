package dev.whitespace.core.service.bundler.visitor;

import dev.whitespace.core.service.bundler.domain.Reference;

public interface AbsoluteVisitor {
	String visit(Reference reference);
}
