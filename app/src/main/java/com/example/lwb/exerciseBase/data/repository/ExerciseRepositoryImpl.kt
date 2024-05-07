package com.example.lwb.exerciseBase.data.repository

import com.example.lwb.core.data.entities.Exercise
import com.example.lwb.exerciseBase.domain.model.ExerciseDetail
import com.example.lwb.exerciseBase.domain.repository.ExerciseRepository

class ExerciseRepositoryImpl: ExerciseRepository {
    override suspend fun getMuscleGroups(): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getExerciseDetailsByName(exerciseName: String): ExerciseDetail {
        TODO("Not yet implemented")
    }

    override suspend fun getExerciseByName(exerciseName: String): List<Exercise> {
        TODO("Not yet implemented")
    }

    override suspend fun getExercisesByMuscleGroup(muscleGroup: String): List<Exercise> {
        TODO("Not yet implemented")
    }
}