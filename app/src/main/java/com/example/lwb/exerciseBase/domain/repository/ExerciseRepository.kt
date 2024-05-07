package com.example.lwb.exerciseBase.domain.repository

import com.example.lwb.core.data.entities.Exercise
import com.example.lwb.exerciseBase.domain.model.ExerciseDetail

interface ExerciseRepository {
    suspend fun getMuscleGroups(): List<String>

    suspend fun getExerciseByName(exerciseName: String): List<Exercise>

    suspend fun getExercisesByMuscleGroup(muscleGroup: String): List<Exercise>

    suspend fun getExerciseDetailsByName(exerciseName: String): ExerciseDetail
}