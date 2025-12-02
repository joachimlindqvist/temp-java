package dev.whitespace.domain.recording

import kotlinx.serialization.Serializable

@Serializable
data class Stylesheet(
    val action: String,
    val atIndex: Int,
    val arguments: List<StylesheetArgument>
)

@Serializable
data class StylesheetArgument(
    val string: String?,
    val number: Int?
)
