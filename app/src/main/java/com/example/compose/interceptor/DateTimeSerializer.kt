package com.example.compose.interceptor

import com.google.gson.*
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateTimeSerializer : JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
    override fun serialize(src: LocalDateTime, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(src.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")))
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): LocalDateTime? {
        return try {
            LocalDateTime.parse(
                json?.asString,
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            )
        } catch (ex: Exception) {
            LocalDateTime.parse(
                json?.asString,
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
            )
        }
    }
}