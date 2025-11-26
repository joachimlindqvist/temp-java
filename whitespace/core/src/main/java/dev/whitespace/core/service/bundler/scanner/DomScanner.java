package dev.whitespace.core.service.bundler.scanner;

import dev.whitespace.core.service.bundler.domain.Recordable;

public class DomScanner {
    static void scan(Recordable.Dom dom, RelativeVisitor visitor) {
        fromSetAttribute(dom, visitor);
    }

    private static void fromSetAttribute(Recordable.Dom dom, RelativeVisitor visitor) {
        if (dom.isProperty("setAttribute") && dom.hasArgumentSize(2)) {
            var first = dom.arguments.get(0);
            var second = dom.arguments.get(1);
            if ("src".equalsIgnoreCase(first.string) && second.string != null) {
                var next = visitor.visit(second.string, "unknown");
                if (next != null) {
                    second.string = next;
                }

            }
        }

    }
}

//public class DomScanner(Dom dom) : IRelativeScanner
//{
//    public void Scan(VisitorRelativeFn visitor)
//    {
//        FromSetAttribute(visitor);
//    }
//
//    private void FromSetAttribute(VisitorRelativeFn visitor)
//    {
//        if (dom.Property.Equals("setattribute", StringComparison.InvariantCultureIgnoreCase) && dom.Arguments.Count == 2)
//        {
//            var (attrArg, valueArg) = (dom.Arguments[0], dom.Arguments[1]);
//            if (attrArg.String == "src" && valueArg.String is { } value && AssetReference.IsExternal(value))
//            {
//                if (visitor(value, "unknown") is { } next)
//                {
//                    valueArg.String = next;
//                }
//            }
//        }
//    }
//}