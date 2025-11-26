package dev.whitespace.core.service.bundler.scanner;

import dev.whitespace.core.service.bundler.domain.CssValue;
import dev.whitespace.core.service.bundler.domain.Recordable;

public class StylesheetScanner {
    static void scan(Recordable.Stylesheet stylesheet, RelativeVisitor visitor) {
        fromArgument(stylesheet, visitor);
    }

    private static void fromArgument(Recordable.Stylesheet stylesheet, RelativeVisitor visitor) {
        for (var arg : stylesheet.arguments) {
            if (arg.string != null) {
                var val = new CssValue(arg.string);
                CssScanner.scan(val, visitor);
            }
        }
    }
}
