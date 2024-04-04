package com.example.lwb.exerciseBase.data.data_source

import com.example.lwb.exerciseBase.domain.model.Exercise
import com.example.lwb.exerciseBase.domain.model.ExerciseDetail

//todo: сюда нужно добавить аннотации для бд, чтобы это было привязано к ней
//Здесь типа запросы к бд
interface ExercisesDao {
    fun getMuscleGroups(): List<String>

    fun getExerciseByName(exerciseName: String): List<Exercise>

    fun getExercisesByMuscleGroup(muscleGroup: String): List<Exercise>

    fun getExerciseDetailsByName(exerciseName: String): ExerciseDetail
}