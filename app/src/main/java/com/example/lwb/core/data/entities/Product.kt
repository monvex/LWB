package com.example.lwb.core.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.lwb.core.data.ImageConverter

@Entity
data class Product(
    @PrimaryKey val id: Int,
    val name: String,
    val calories: Int,
    val proteins: Int,
    val fats: Int,
    val carbohydrates: Int,
    @TypeConverters(ImageConverter::class)
    val image: ByteArray
)