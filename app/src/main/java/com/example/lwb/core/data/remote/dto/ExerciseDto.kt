package com.example.lwb.core.data.remote.dto

data class ExerciseDto(
    val id: Int,
    val label: String,
    val muscleGroup: String?,
    val description: String,
    val image: String?, // Base64 encoded string
    val image2: String? // Base64 encoded string
)