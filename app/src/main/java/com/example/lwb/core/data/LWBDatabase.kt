package com.example.lwb.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lwb.core.data.dao.*
import com.example.lwb.core.data.entities.*

@Database(entities = [User::class, Product::class, Exercise::class, Training::class, Day::class, Meal::class], version = 1, exportSchema = false)
abstract class LWBDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun trainingDao(): TrainingDao
    abstract fun dayDao(): DayDao
    abstract fun mealDao(): MealDao
}