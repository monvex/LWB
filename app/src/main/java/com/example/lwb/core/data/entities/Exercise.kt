package com.example.lwb.core.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.lwb.core.data.ImageConverter

@Entity
data class Exercise(
    @PrimaryKey val id: Int?,
    val name: String?,
    val muscleGroup: String?,
    val description: String?,
    val imageFirst: String?,
    val imageSecond: String?
)