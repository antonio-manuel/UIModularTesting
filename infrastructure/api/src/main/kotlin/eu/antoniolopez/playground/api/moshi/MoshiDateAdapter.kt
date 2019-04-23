package eu.antoniolopez.playground.api.moshi

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.io.IOException
import java.util.*

class MoshiDateAdapter : JsonAdapter<Date>() {

    @Synchronized
    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): Date {
        val long = reader.nextString().toLong()
        return Date(long)
    }

    @Synchronized
    @Throws(IOException::class)
    override fun toJson(writer: JsonWriter, value: Date?) {
        val long = value?.time
            ?: throw JsonDataException("Invalid Date: $value")
        writer.value(long)
    }
}
