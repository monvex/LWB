package com.example.lwb.exerciseBase.presentation.exercisePage

data class ExercisePageState(
    val searchQuery: String = "",
    val muscleGroups: List<String> = emptyList()
)