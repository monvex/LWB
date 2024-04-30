package com.example.lwb.core.data.converters

import com.google.gson.Gson


// Класс для конвертации JSON в объекты и наоборот
class Converters {
    @androidx.room.TypeConverter
    fun listToJson(value: List<String>?): String = Gson().toJson(value)

    @androidx.room.TypeConverter
    fun jsonToList(value: String): List<String> = Gson().fromJson(value, Array<String>::class.java).toList()
}