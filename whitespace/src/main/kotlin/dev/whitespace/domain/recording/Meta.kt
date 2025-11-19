package dev.whitespace.domain.recording

import kotlinx.serialization.Serializable

@Serializable
data class Meta(val frame: Int, val transpiredAt: Int)
