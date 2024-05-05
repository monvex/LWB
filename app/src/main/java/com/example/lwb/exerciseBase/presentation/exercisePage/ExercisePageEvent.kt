package com.example.lwb.exerciseBase.presentation.exercisePage

sealed class ExercisePageEvent {
    data class OnSearchQueryChange(val query: String) : ExercisePageEvent()
    data class OnMuscleGroupClick(val muscleGroup: String) : ExercisePageEvent()
}