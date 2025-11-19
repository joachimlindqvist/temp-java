package org.acme.bundler.types;

import java.io.Serializable;
import java.net.URI;

public record Location(String location, URI base) implements Serializable {}
