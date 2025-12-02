package dev.whitespace.service.bundler

import java.net.URI

data class Reference(
    val ref: String,
    val base: URI,
    val resourceType: String,
) {
    fun url(): URI {
        return base.resolve(ref)
    }
}
