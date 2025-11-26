package dev.whitespace.core.service.bundler.scanner;

import dev.whitespace.core.service.bundler.domain.Recordable;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RecordableScanner {
    private final List<Recordable> recordables;

    void scan(VisitorFn visitorFn) {
        var relativeVisitor = new RelativeVisitor();

        for (var recordable : recordables) {
            if (recordable.location != null) {
                relativeVisitor.setBase(recordable.location.base.resolve(recordable.location.location));
            } else if (recordable.dom != null) {
                DomScanner.scan(recordable.dom, relativeVisitor);
            }
        }

    }
}


//using Whitespace.Domain.Recording;
//
//namespace Whitespace.Services.RecordingBundler.Scanners;
//
//internal interface IScanner
//{
//    void Scan(VisitorFn visitor);
//}
//
//public class RecordablesScanner(IEnumerable<Recordable> recordables) : IScanner
//{
//    public void Scan(VisitorFn visitor)
//    {
//        Uri? @base = null;
//
//        VisitorRelativeFn wrappedVisitor = (reference, type) =>
//        {
//            if (@base is not null && Uri.TryCreate(reference, UriKind.RelativeOrAbsolute, out var relative))
//            {
//                return visitor(@base.MakeRelativeUri(relative));
//            }
//
//            throw new InvalidOperationException("base not set in visitor function");
//        };
//
//        foreach (var recordable in recordables)
//        {
//            if (recordable.Location is { } location)
//            {
//                @base = location.Base;
//            }
//            else if (recordable.Dom is { } dom)
//            {
//                new DomScanner(dom).Scan(wrappedVisitor);
//            }
//        }
//    }
//}