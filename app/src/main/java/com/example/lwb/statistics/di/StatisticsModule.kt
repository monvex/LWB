package com.example.lwb.statistics.di

import com.example.lwb.foodDiary.data.remote.Api
import com.example.lwb.foodDiary.presentation.diary.components.FoodAlgoritms
import com.example.lwb.statistics.presentation.main.components.StatisticAlgoritms
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //Время жизни модуля
object StatisticsModule {
    @Provides
    @Singleton //Как много объектов класса мы создаем, когда вызываем эту тему. Если по умному - Scope Annotation
    fun provideApi(): Api {
        return Retrofit.Builder()
            .baseUrl("https://apiurl.com") //TODO: заменить на настоящюю ссылку
            .build()
            .create(Api::class.java)
    }

    @Provides
    fun provideStatisticAlgoritms(): StatisticAlgoritms {
        return StatisticAlgoritms()
    }
}