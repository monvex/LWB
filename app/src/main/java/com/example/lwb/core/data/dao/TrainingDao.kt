package com.example.lwb.core.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lwb.core.data.entities.Training

@Dao
interface TrainingDao {
    @Query("SELECT * FROM training")
    fun getAll(): List<Training>

    @Insert
    fun insert(training: Training)

    @Delete
    fun delete(training: Training)
}