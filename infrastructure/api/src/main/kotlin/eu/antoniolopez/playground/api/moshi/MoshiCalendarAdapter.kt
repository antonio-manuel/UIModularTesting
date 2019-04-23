package eu.antoniolopez.playground.api.moshi

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.io.IOException
import java.util.*

class MoshiCalendarAdapter : JsonAdapter<Calendar>() {

    @Synchronized
    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): Calendar {
        val long = reader.nextString().toLong()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = long
        return calendar
    }

    @Synchronized
    @Throws(IOException::class)
    override fun toJson(writer: JsonWriter, value: Calendar?) {
        val long = value?.timeInMillis
            ?: throw JsonDataException("Invalid Calendar: $value")
        writer.value(long)
    }
}
