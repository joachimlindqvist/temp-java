package dev.whitespace.domain.recording

import kotlinx.serialization.Serializable

@Serializable
data class Log(val level: String, val message: String)
