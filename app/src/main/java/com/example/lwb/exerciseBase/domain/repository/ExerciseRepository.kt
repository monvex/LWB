package com.example.lwb.exerciseBase.domain.repository

interface ExerciseRepository {

    suspend fun GetMuscleGroups(): List<String>

    suspend fun GetExerciseByName(exerciseName: String): ExerciseDetail

    suspend fun GetExercisesByMuscleGroup(muscleGroup: String): ExerciseDetail
}