package dev.whitespace.core.service.bundler.scanner;

import dev.whitespace.core.service.bundler.domain.CssValue;
import dev.whitespace.core.service.bundler.visitor.RelativeVisitor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CssScanner {
    private static final Pattern cssImportDoubleQuotedPattern = Pattern.compile("@import\\s*\"(?<ref>[^\"]+)\"");
    private static final Pattern cssImportSingleQuotedPattern = Pattern.compile("@import\\s*'(?<ref>[^\"']+)'");
    private static final Pattern cssUrlDoubleQuotedPattern = Pattern.compile("url\\(\"(?<ref>[^\"]+)\"\\)");
    private static final Pattern cssUrlSingleQuotedPattern = Pattern.compile("url\\('(?<ref>[^']+')\\)");
    private static final Pattern cssUrlUnquotePattern = Pattern.compile("url\\((?<ref>[^)]+)\\)");
    private static final Pattern cssSourceMapPattern = Pattern.compile("sourceMappingURL=(?<ref>[^*]+)");

    static void scan(CssValue css, RelativeVisitor visitor) {
        scanCssFromImportPattern(css, visitor);
        scanCssFromUrlPattern(css, visitor);
        scanCssFromSourceMapPattern(css, visitor);
    }

    private static void scanCssFromImportPattern(CssValue css, RelativeVisitor visitor) {
        scanCssFromPattern(cssImportDoubleQuotedPattern, css, visitor);
        scanCssFromPattern(cssImportSingleQuotedPattern, css, visitor);
    }

    private static void scanCssFromUrlPattern(CssValue css, RelativeVisitor visitor) {
        scanCssFromPattern(cssUrlDoubleQuotedPattern, css, visitor);
        scanCssFromPattern(cssUrlSingleQuotedPattern, css, visitor);
        scanCssFromPattern(cssUrlUnquotePattern, css, visitor);
    }

    private static void scanCssFromSourceMapPattern(CssValue css, RelativeVisitor visitor) {
        scanCssFromPattern(cssSourceMapPattern, css, visitor);
    }

    private static void scanCssFromPattern(Pattern pattern, CssValue css, RelativeVisitor visitor) {
        var matcher = pattern.matcher(css.getValue());

        var sb = new StringBuilder();

        while (matcher.find()) {
            var ref = matcher.group("ref");
            var next = visitor.visit(ref, "unknown");
            if (next != null) {
                matcher.appendReplacement(sb, Matcher.quoteReplacement(next));
            }
        }

        if (!sb.isEmpty()) {
            matcher.appendTail(sb);
            css.setValue(sb.toString());
        }
    }
}
