package dev.whitespace.service.bundler.scanner;

import dev.whitespace.service.bundler.domain.Recordable;
import dev.whitespace.service.bundler.visitor.RelativeVisitor;

public class DomScanner {
    static void scan(Recordable.Dom dom, RelativeVisitor visitor) {
        fromSetAttribute(dom, visitor);
//		fromPropertySetter(dom, visit);
//		fromStylesheetModifier(dom, visit);
		fromNodeArguments(dom, visitor);
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

	private static void fromNodeArguments(Recordable.Dom dom, RelativeVisitor visitor) {
		for (var argument : dom.arguments) {
			if (argument.node != null) {
				HtmlScanner.scan(argument.node, visitor);
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