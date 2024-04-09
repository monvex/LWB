package com.example.lwb.foodDiary.di

import com.example.lwb.foodDiary.data.repository.FoodDiaryRepositoryImpl
import com.example.lwb.foodDiary.domain.repository.FoodDiaryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


//То же самое, что и в provides в FoodModule, но эта байда генерирует меньше кода и вцелом вроде как лучше писать так
//Можно использовать для передачи зависимостей интерфейсам и абстрактным классам
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideFoodDiaryRepository(
        repositoryImpl: FoodDiaryRepositoryImpl
    ): FoodDiaryRepository
}