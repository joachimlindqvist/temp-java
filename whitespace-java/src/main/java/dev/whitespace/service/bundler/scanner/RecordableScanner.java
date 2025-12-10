package dev.whitespace.service.bundler.scanner;

import dev.whitespace.service.bundler.domain.Recordable;
import dev.whitespace.service.bundler.domain.Reference;
import dev.whitespace.service.bundler.visitor.AbsoluteVisitor;
import dev.whitespace.service.bundler.visitor.FindingVisitor;
import dev.whitespace.service.bundler.visitor.RelativeVisitor;
import dev.whitespace.service.bundler.visitor.ReplacingVisitor;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
public class RecordableScanner {
    private final List<Recordable> recordables;

	public List<Reference> find() {
		var visitor = new FindingVisitor();
		scan(visitor);
		return visitor.getReferences().values().stream().toList();
	}

	public void replace(HashMap<URI, Reference> replacements) {
		scan(new ReplacingVisitor(replacements));
	}

	private void scan(AbsoluteVisitor visitor) {
        var relativeVisitor = new RelativeVisitor(visitor);

        for (var recordable : recordables) {
            if (recordable.location != null) {
                relativeVisitor.setBase(recordable.location.base.resolve(recordable.location.location));
            } else if (recordable.dom != null) {
                DomScanner.scan(recordable.dom, relativeVisitor);
            } else if (recordable.stylesheet != null) {
				StylesheetScanner.scan(recordable.stylesheet, relativeVisitor);
			}
        }
    }
}
