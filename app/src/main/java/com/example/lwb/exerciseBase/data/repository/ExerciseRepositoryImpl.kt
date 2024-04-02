package com.example.lwb.exerciseBase.data.repository

import com.example.lwb.exerciseBase.domain.repository.ExerciseRepository

class ExerciseRepositoryImpl: ExerciseRepository {
    override suspend fun GetMuscleGroups(): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun GetExerciseByName(exerciseName: String): ExerciseDetail {
        TODO("Not yet implemented")
    }

    override suspend fun GetExercisesByMuscleGroup(muscleGroup: String): ExerciseDetail {
        TODO("Not yet implemented")
    }
}