package com.example.lwb.core.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Day(
    @PrimaryKey val date: String,
    val weight: Int,
    val waterIntake: Int,
    val calorieIntake: Int,
    val proteinIntake: Int,
    val fatIntake: Int,
    val carbohydrateIntake: Int
)