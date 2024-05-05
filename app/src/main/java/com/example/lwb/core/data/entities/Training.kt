package com.example.lwb.core.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Training(
    @PrimaryKey val id: Int,
    val name: String,
    val type: String,
    val muscleGroup: String,
    val difficulty: String,
    val exercises: String
)