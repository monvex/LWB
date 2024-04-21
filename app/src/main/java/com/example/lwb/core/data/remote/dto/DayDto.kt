package com.example.lwb.core.data.remote.dto

data class DayDto(
    val id: Int,
    val userId: Int,
    val date: String,
    val weight: Float,
    val waterIntake: Float,
    val caloriesIntake: Float,
    val proteinsIntake: Float,
    val fatsIntake: Float,
    val carbsIntake: Float
)