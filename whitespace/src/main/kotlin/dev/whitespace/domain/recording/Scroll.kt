package dev.whitespace.domain.recording

import kotlinx.serialization.Serializable

@Serializable
data class Scroll(val target: String, val x: Float, val y: Float)
