package dev.whitespace.core.service.bundler.domain;


import org.jspecify.annotations.Nullable;

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
