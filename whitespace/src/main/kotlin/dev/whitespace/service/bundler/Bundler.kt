package dev.whitespace.service.bundler

import dev.whitespace.domain.recording.Recordable
import io.quarkus.logging.Log
import java.nio.file.Path
import kotlinx.serialization.json.Json
import java.lang.Exception
import kotlin.io.path.readLines

class Bundler(private val input: Input) {
    suspend fun run() {
    }
}


data class Input(val recordables: List<Recordable>) {
    companion object {
        val json = Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }

        fun fromPath(path: Path): Input {
            val recordables = path.readLines(Charsets.UTF_8).mapIndexed { i, r ->
                try {
                    json.decodeFromString<Recordable>(r)
                } catch (e: Exception) {
                    Log.error("error when parsing line at index $i")
                    throw e
                }
            }

            return Input(recordables)
        }
    }
}

