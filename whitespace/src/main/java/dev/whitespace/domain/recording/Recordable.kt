package dev.whitespace.domain.recording

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Recordable(
    val meta: Meta,
    val cursor: Cursor?,
    val location: Location?,
    val storage: Storage?,
    @SerialName("window_size") val windowSize: WindowSize?,
    val log: Log?,
    val dom: Dom?,
    val scroll: Scroll?,
    @SerialName("http_request") val httpRequest: HttpRequest?,
    @SerialName("http_response") val httpResponse: HttpResponse?,
    val http: HttpTransaction?,
    val stylesheet: Stylesheet?,
    val userInput: UserInput?,
)
