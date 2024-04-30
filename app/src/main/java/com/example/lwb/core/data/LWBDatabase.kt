package com.example.lwb.core.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lwb.core.data.converters.Converters
import com.example.lwb.exerciseBase.data.data_source.ExercisesDao
import com.example.lwb.exerciseBase.domain.model.Exercise
import com.example.lwb.foodDiary.data.data_source.FoodDiaryDao
import com.example.lwb.foodDiary.domain.model.Product
import com.example.lwb.onboarding.data.data_source.UserOnBoardingDao
import java.sql.Connection
import java.sql.DriverManager

@Database(
    entities = [Exercise::class, Product::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class LWBDatabase : RoomDatabase() {

    //abstract val exercisesDao: ExercisesDao
    //abstract val foodDiaryDao: FoodDiaryDao
    //abstract val userOnBoardingDao: UserOnBoardingDao

    companion object {
        @Volatile
        private var INSTANCE: LWBDatabase? = null

        fun getInstance(context: Context): LWBDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    LWBDatabase::class.java,
                    "lwb_db"
                ).fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
        }

        fun createCloudSqlConnection(): Connection {
            val url = "jdbc:mysql://localhost:3306/LWB"
            val user = "root"
            val password = "test"
            return DriverManager.getConnection(url, user, password)
        }
    }
}
