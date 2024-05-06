package com.example.lwb.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.lwb.core.data.LWBDatabase
import com.example.lwb.core.data.dao.ExerciseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideLWBDatabase(@ApplicationContext context: Context): LWBDatabase {
        return Room.databaseBuilder(
            context,
            LWBDatabase::class.java, "lwb.db"
        )
            .createFromAsset("lwb.db") // Использование предварительно заполненной базы данных
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                }
            })
            .build()
    }

    @Provides
    fun provideExerciseDao(database: LWBDatabase): ExerciseDao {
        return database.exerciseDao()
    }
}