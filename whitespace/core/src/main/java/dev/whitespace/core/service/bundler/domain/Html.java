package dev.whitespace.core.service.bundler.domain;


import org.jspecify.annotations.Nullable;
import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.List;

public class Html {
    public static class Node {
        public Integer nodeType = -1;
        public @Nullable String tagName;
        public @Nullable String nodeValue;
        public @Nullable String nodeName;
        public @Nullable String namespace;
        public @Nullable List<Attribute> attributes;
        public @Nullable List<Property> properties;
        public @Nullable List<Node> children;
        public @Nullable Node shadowRoot;

		public boolean hasTagName(String tagName) {
			if (this.tagName == null) {
				return false;
			}
			return this.tagName.equalsIgnoreCase(tagName);
		}

		public @Nullable Attribute getAttribute(String attributeName) {
			for (var attribute : attributes) {
				if (attribute.name.equalsIgnoreCase(attributeName)) {
					return attribute;
				}
			}
			return null;
		}

		public @Nullable Attribute getAttributeNS(String attributeName, String namespace) {
			for (var attribute : attributes) {
				if (attribute.namespace == null) {
					continue;
				}

				if (attribute.name.equalsIgnoreCase(attributeName) && attribute.namespace.equals(namespace)) {
					return attribute;
				}
			}
			return null;
		}

		public List<Attribute> getAttributeStartingWith(String prefix) {
			return attributes.stream().filter(attr -> attr.name.startsWith(prefix)).toList();
		}

		public static class Attribute {
            public String name;
            public String value;
            public @Nullable String namespace;
        }

        public static class Property {
            public String name;
            public String value;
        }
    }
}
