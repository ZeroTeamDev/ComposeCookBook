package com.base.project.core.database.converter

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

/**
 * Type converters for Room database
 * Handles conversion between complex types and primitive types that Room can store
 */
class DatabaseTypeConverters {
    
    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
    
    // Date/Time Converters
    @TypeConverter
    fun fromInstant(instant: Instant?): String? = instant?.toString()
    
    @TypeConverter
    fun toInstant(value: String?): Instant? = value?.let { Instant.parse(it) }
    
    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime?): String? = dateTime?.toString()
    
    @TypeConverter
    fun toLocalDateTime(value: String?): LocalDateTime? = value?.let { LocalDateTime.parse(it) }
    
    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? = date?.toString()
    
    @TypeConverter
    fun toLocalDate(value: String?): LocalDate? = value?.let { LocalDate.parse(it) }
    
    // List Converters
    @TypeConverter
    fun fromStringList(list: List<String>?): String? {
        return list?.let { json.encodeToString(it) }
    }
    
    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        return value?.let { json.decodeFromString(it) }
    }
    
    @TypeConverter
    fun fromIntList(list: List<Int>?): String? {
        return list?.let { json.encodeToString(it) }
    }
    
    @TypeConverter
    fun toIntList(value: String?): List<Int>? {
        return value?.let { json.decodeFromString(it) }
    }
    
    @TypeConverter
    fun fromLongList(list: List<Long>?): String? {
        return list?.let { json.encodeToString(it) }
    }
    
    @TypeConverter
    fun toLongList(value: String?): List<Long>? {
        return value?.let { json.decodeFromString(it) }
    }
    
    // Map Converters
    @TypeConverter
    fun fromStringMap(map: Map<String, String>?): String? {
        return map?.let { json.encodeToString(it) }
    }
    
    @TypeConverter
    fun toStringMap(value: String?): Map<String, String>? {
        return value?.let { json.decodeFromString(it) }
    }
    
    @TypeConverter
    fun fromStringAnyMap(map: Map<String, Any>?): String? {
        return map?.let { json.encodeToString(it) }
    }
    
    @TypeConverter
    fun toStringAnyMap(value: String?): Map<String, Any>? {
        return value?.let { json.decodeFromString(it) }
    }
    
    // Set Converters
    @TypeConverter
    fun fromStringSet(set: Set<String>?): String? {
        return set?.let { json.encodeToString(it.toList()) }
    }
    
    @TypeConverter
    fun toStringSet(value: String?): Set<String>? {
        return value?.let { json.decodeFromString<List<String>>(it).toSet() }
    }
    
    // Enum Converters (Generic example)
    @TypeConverter
    fun fromEnum(enum: Enum<*>?): String? = enum?.name
    
    @TypeConverter
    fun toEnum(value: String?): Enum<*>? {
        // This would need to be specialized for specific enums
        return null
    }
}

/**
 * Specialized type converters for common business objects
 */
class BusinessTypeConverters {
    
    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
    
    // Common business objects can be added here
    // Example:
    /*
    @TypeConverter
    fun fromAddress(address: Address?): String? {
        return address?.let { json.encodeToString(it) }
    }
    
    @TypeConverter
    fun toAddress(value: String?): Address? {
        return value?.let { json.decodeFromString(it) }
    }
    */
}

/**
 * Utility functions for type conversion
 */
object TypeConverterUtils {
    
    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
    
    /**
     * Generic converter for any serializable object
     */
    inline fun <reified T> objectToString(obj: T?): String? {
        return obj?.let { json.encodeToString(it) }
    }
    
    /**
     * Generic converter from string to any serializable object
     */
    inline fun <reified T> stringToObject(value: String?): T? {
        return value?.let { json.decodeFromString(it) }
    }
    
    /**
     * Convert any list to string
     */
    inline fun <reified T> listToString(list: List<T>?): String? {
        return list?.let { json.encodeToString(it) }
    }
    
    /**
     * Convert string to any list
     */
    inline fun <reified T> stringToList(value: String?): List<T>? {
        return value?.let { json.decodeFromString(it) }
    }
}

/**
 * Extension functions for easy type conversion
 */
fun <T> T?.toJsonString(): String? = TypeConverterUtils.objectToString(this)

inline fun <reified T> String?.fromJsonString(): T? = TypeConverterUtils.stringToObject<T>(this) 