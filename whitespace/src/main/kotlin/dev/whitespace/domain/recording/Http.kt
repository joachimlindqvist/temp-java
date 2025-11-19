package dev.whitespace.domain.recording

import kotlinx.serialization.Serializable

@Serializable
data class HttpRequest(
    val headers: Map<String, List<String>>,
    var method: String,
    var url: String,
    var requestId: String,
    var body: String?
)

@Serializable
data class HttpResponse(
    val headers: Map<String, List<String>>,
    var method: String,
    var url: String,
    var requestId: String,
    var status: Int,
    var body: String?
)

@Serializable
data class HttpTransaction(
    val request: HttpRequest?,
    val requestMeta: Meta?,
    val response: HttpResponse?,
    val responseMeta: Meta?,
)

