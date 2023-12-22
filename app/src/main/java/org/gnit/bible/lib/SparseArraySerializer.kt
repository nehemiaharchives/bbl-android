package org.gnit.bible.lib

import android.util.SparseArray
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor

import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object SparseArraySerializer : KSerializer<SparseArray<String>>{
    private val mapSerializer = MapSerializer(Int.serializer(), String.serializer())

    override val descriptor = PrimitiveSerialDescriptor("SparseArray", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: SparseArray<String>) {
        // Convert SparseArray to a serializable format, e.g., a Map
        val map: MutableMap<Int, String> = mutableMapOf()
        for (index in 0 until value.size()) {
            map[value.keyAt(index)] = value.valueAt(index)
        }

        mapSerializer.serialize(encoder, map)
    }

    override fun deserialize(decoder: Decoder): SparseArray<String> {
        // Decode into a Map and then create a SparseArray
        val map = mapSerializer.deserialize(decoder)
        val size = map.size
        val array = SparseArray<String>(size)

        map.forEach { (key, value) ->
            array.put(key, value)
        }

        return array
    }
}