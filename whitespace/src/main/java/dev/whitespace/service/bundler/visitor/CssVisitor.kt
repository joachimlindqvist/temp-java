package dev.whitespace.service.bundler.visitor

import dev.whitespace.domain.recording.Css

object CssVisitor {
    public fun scan(css: Css, fn: () -> Unit) {
        css.value = ""
    }
}