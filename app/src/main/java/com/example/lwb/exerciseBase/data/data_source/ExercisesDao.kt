package com.example.lwb.exerciseBase.data.data_source

import androidx.room.Dao
import com.example.lwb.core.data.entities.Exercise
import com.example.lwb.exerciseBase.domain.model.ExerciseDetail

//todo: сюда нужно добавить аннотации для бд, чтобы это было привязано к ней
//Здесь типа запросы к бд
@Dao
interface ExercisesDao {
    fun getMuscleGroups(): List<String>

    fun getExerciseByName(exerciseName: String): List<Exercise>

    fun getExercisesByMuscleGroup(muscleGroup: String): List<Exercise>

    fun getExerciseDetailsByName(exerciseName: String): ExerciseDetail
}