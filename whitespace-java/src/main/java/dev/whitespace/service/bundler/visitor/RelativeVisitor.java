package dev.whitespace.service.bundler.visitor;

import dev.whitespace.service.bundler.domain.Reference;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.net.URI;

@RequiredArgsConstructor
@Setter
public class RelativeVisitor {
	private final AbsoluteVisitor visitor;

	private URI base;

	public String visit(String reference, String type) {
		if (base == null) {
			throw new RuntimeException("A location recordable must be recorded before any dependant recordables.");
		}

		try {
			return visitor.visit(new Reference(reference, base, type));
		} catch (Exception e) {
			return base.toString();
		}
	}
}
