package dev.whitespace.core.service.bundler.visitor;

import java.net.URI;

import dev.whitespace.core.service.bundler.domain.Reference;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
