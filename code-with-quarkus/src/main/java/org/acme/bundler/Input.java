package org.acme.bundler;

import org.acme.bundler.types.Recordable;

import java.util.List;

public record Input(List<Recordable> recordables) { }
