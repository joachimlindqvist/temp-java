package dev.whitespace.service.bundler.scanner;

import dev.whitespace.service.bundler.domain.CssValue;
import dev.whitespace.service.bundler.domain.Html;
import dev.whitespace.service.bundler.domain.Reference;
import dev.whitespace.service.bundler.visitor.RelativeVisitor;

public class HtmlScanner {
    static void scan(Html.Node node, RelativeVisitor visitor) {
		fromSourceableNode(node, visitor);
		fromLinkNode(node, visitor);
		fromVideoNode(node, visitor);
		fromObjectNode(node, visitor);
		fromUseNode(node, visitor);
		fromStyleNode(node, visitor);
		fromStyleAttribute(node, visitor);
		fromChildren(node, visitor);
		fromShadowRoot(node, visitor);
	}

    private static void fromSourceableNode(Html.Node node, RelativeVisitor visitor) {
		if (node.hasTagName("img") || node.hasTagName("source")) {
			var src = node.getAttribute("src");
			if (src != null && Reference.isExternal(src.value)) {
				var next = visitor.visit(src.value, "image");
				if (next != null) {
					src.value = next;
				}
			}

			var srcset = node.getAttribute("srcset");
			if (srcset != null) {
				// TODO: parse srcset
//				srcsetValue := ss.Parse(srcset.Value)
//				changed := false
//				for i := range srcsetValue {
//					value := &srcsetValue[i]
//					if next := visit(value.URL, "image"); next != nil {
//						value.URL = *next
//						changed = true
//					}
//
//				}
//				if changed {
//					srcset.Value = srcsetValue.String()
//				}
			}
		}
    }

	private static void fromLinkNode(Html.Node node, RelativeVisitor visitor) {
		if (node.hasTagName("link")) {
			var rel = node.getAttribute("rel");
			if (rel != null) {
				var relevant = rel.value.equals("stylesheet") ||
						rel.value.equals("icon") ||
						rel.value.equals("manifest") ||
						rel.value.equals("preload") ||
						rel.value.equals("prefetch");
				if (relevant) {
					var href = node.getAttribute("href");
					if (href != null && Reference.isExternal(href.value)) {
						var next = visitor.visit(href.value, "stylesheet");
						if (next != null) {
							href.value = next;
						}
					}
				}
			}
		}
	}


	private static void fromVideoNode(Html.Node node, RelativeVisitor visitor) {
		if (node.hasTagName("video")) {
			var poster = node.getAttribute("poster");
			if (poster != null && Reference.isExternal(poster.value)) {
				var next = visitor.visit(poster.value, "stylesheet");
				if (next != null) {
					poster.value = next;
				}
			}
		}
	}

	private static void fromObjectNode(Html.Node node, RelativeVisitor visitor) {
		if (node.hasTagName("object")) {
			var type = node.getAttribute("type");
			if (type != null && type.value.startsWith("image/")) {
				var data =  node.getAttribute("data");
				if (data != null && Reference.isExternal(data.value)) {
					var next = visitor.visit(data.value, "image");
					if (next != null) {
						data.value = next;
					}
				}
			}
		}
	}

	private static void fromUseNode(Html.Node node, RelativeVisitor visitor) {
		// `use` element with a fragment `href`/`xlink:href` won't count as
		// external refs as the ref will be locally defined in the document.
		if (node.hasTagName("use")) {
			var href = node.getAttribute("href");
			if (href != null && Reference.isExternal(href.value)) {
				var next = visitor.visit(href.value, "image");
				if (next != null) {
					href.value = next;
				}
			}

			var xlinkHref = node.getAttributeNS("href", "xlink");
			if (xlinkHref != null && Reference.isExternal(xlinkHref.value)) {
				var next = visitor.visit(xlinkHref.value, "image");
				if (next != null) {
					xlinkHref.value = next;
				}
			}
		}
	}

	private static void fromStyleNode(Html.Node node, RelativeVisitor visitor) {
		if (node.hasTagName("style")) {
			for (var child : node.children) {
				var hasValue = child.nodeName != null &&
                        !child.nodeName.isEmpty() &&
						child.nodeValue != null &&
                        !child.nodeValue.isEmpty();
				if (hasValue && child.nodeName.equalsIgnoreCase("#text")) {
					var css = new CssValue(child.nodeValue);
					CssScanner.scan(css, visitor);
					child.nodeValue = css.getValue();
				}
			}
		}
	}

	private static void fromStyleAttribute(Html.Node node, RelativeVisitor visitor) {
		var style = node.getAttribute("style");
		if (style != null) {
			var css = new CssValue(style.value);
			CssScanner.scan(css, visitor);
			style.value = css.getValue();
		}
	}

	private static void fromChildren(Html.Node node, RelativeVisitor visitor) {
		for (var child : node.children) {
			HtmlScanner.scan(child, visitor);
		}
	}

	private static void fromShadowRoot(Html.Node node, RelativeVisitor visitor) {
		if (node.shadowRoot != null) {
			HtmlScanner.scan(node.shadowRoot, visitor);
		}
	}
}
