package com.example.lwb.core.di

import android.content.Context
import androidx.room.Room
import com.example.lwb.core.data.LWBDatabase
import com.example.lwb.core.data.dao.ExerciseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideLWBDatabase(@ApplicationContext context: Context): LWBDatabase {
        return Room.databaseBuilder(
            context,
            LWBDatabase::class.java, "lwb_database"
        ).build()
    }

    @Provides
    fun provideExerciseDao(database: LWBDatabase): ExerciseDao {
        return database.exerciseDao()
    }
}