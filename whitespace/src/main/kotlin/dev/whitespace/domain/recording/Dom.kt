package dev.whitespace.domain.recording

import kotlinx.serialization.Serializable

@Serializable
data class Dom(
    val property: String,
    val target: String,
    val arguments: List<DomArgument>,
)

@Serializable
data class DomArgument(
    val string: String?,
    val number: Float?,
    val json: String?,
    val boolean: Boolean?,
    val undefined: String?,
    val ref: String?,
    val node: HtmlNode?,
)

