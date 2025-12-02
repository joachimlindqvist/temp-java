package dev.whitespace.domain.recording

import kotlinx.serialization.Serializable

@Serializable
data class Storage(
    val property: String,
    val type: String,
    val arguments: List<String>,
)
