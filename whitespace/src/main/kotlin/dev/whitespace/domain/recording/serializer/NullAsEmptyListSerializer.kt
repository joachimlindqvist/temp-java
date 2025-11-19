package dev.whitespace.domain.recording.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class NullAsEmptyListSerializer<T>(
    private val dataSerializer: KSerializer<T>
) : KSerializer<List<T>> {

    private val listSerializer = ListSerializer(dataSerializer)

    override val descriptor = listSerializer.descriptor

    override fun serialize(encoder: Encoder, value: List<T>) {
        listSerializer.serialize(encoder, value)
    }

    override fun deserialize(decoder: Decoder): List<T> {
        return try {
            listSerializer.deserialize(decoder)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
