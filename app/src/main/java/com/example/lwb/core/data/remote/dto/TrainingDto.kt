package com.example.lwb.core.data.remote.dto

data class TrainingDto(
    val id: Int,
    val label: String,
    val muscleGroup: String?,
    val difficulty: String?,
    val exerciseList: List<Exercise>
)

data class Exercise(
    val id: Int,
    val weight: Float,
    val reps: Int,
    val sets: Int
)