package com.example.lwb.core.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Day(
    @PrimaryKey val date: String,
    val weight: Int = 0,
    val waterIntake: Int = 0,
    val caloriesIntake: Int = 0,
    val proteinsIntake: Int = 0,
    val fatsIntake: Int = 0,
    val carbohydratesIntake: Int = 10,
    val recommendedWater: Int = 10,
    val recommendedCalories: Int = 10,
    val recommendedProteins: Int = 10,
    val recommendedFats: Int = 10,
    val recommendedCarbohydrates: Int = 10
)