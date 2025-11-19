package dev.whitespace.domain.recording

import dev.whitespace.domain.recording.serializer.URLSerializer
import kotlinx.serialization.Serializable
import java.net.URL

@Serializable
data class Location(
    val location: String,
    @Serializable(with = URLSerializer::class) val base: URL
)
