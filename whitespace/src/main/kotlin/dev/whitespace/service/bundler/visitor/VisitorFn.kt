package dev.whitespace.service.bundler.visitor

import dev.whitespace.service.bundler.Reference

typealias VisitorFn = (ref: Reference) -> String?
typealias VisitorRawFn = (ref: String, typ: String) -> String?
