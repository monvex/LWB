package com.example.lwb.core.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lwb.core.data.entities.Exercise

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercise")
    suspend fun getAll(): List<Exercise>

    // Поиск по имени
    @Query("SELECT * FROM exercise WHERE LOWER(name) LIKE LOWER(:name) || '%'")
    suspend fun findByName(name: String): List<Exercise>

    // Получение всех групп мышц
    @Query("SELECT DISTINCT muscleGroup FROM exercise")
    suspend fun getAllMuscleGroups(): List<String>

    // Получение по группе
    @Query("SELECT * FROM exercise WHERE muscleGroup = :muscleGroup")
    suspend fun getByMuscleGroup(muscleGroup: String): List<Exercise>

    // Получение по id
    @Query("SELECT * FROM exercise WHERE id = :id")
    suspend fun getById(id: Int): Exercise?

    @Insert
    fun insert(exercise: Exercise)

    @Delete
    fun delete(exercise: Exercise)
}