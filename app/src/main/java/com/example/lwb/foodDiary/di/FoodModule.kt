package com.example.lwb.foodDiary.di

import android.app.Application
import com.example.lwb.core.data.LWBDatabase
import com.example.lwb.core.data.dao.UserDao
import com.example.lwb.foodDiary.data.remote.Api
import com.example.lwb.foodDiary.data.repository.FoodDiaryRepositoryImpl
import com.example.lwb.foodDiary.domain.repository.FoodDiaryRepository
import com.example.lwb.foodDiary.presentation.diary.components.FoodAlgoritms
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //Время жизни модуля
object FoodModule {
    @Provides
    @Singleton //Как много объектов класса мы создаем, когда вызываем эту тему. Если по умному - Scope Annotation
    fun provideApi(): Api {
        return Retrofit.Builder()
            .baseUrl("https://apiurl.com") //TODO: заменить на настоящюю ссылку
            .build()
            .create(Api::class.java)
    }

// Аналог в соседнем файле
// При использовании @Provides, ненужно использовать @Inject constructor() в RepositoryImpl, при использовании @Binds, нужно
//    @Provides
//    @Singleton
//    fun provideFoodDiaryRepository(api: Api): FoodDiaryRepository {
//        return FoodDiaryRepositoryImpl(api)
//    }

    @Provides
    fun provideFoodAlgoritms(): FoodAlgoritms {
        return FoodAlgoritms()
    }

}