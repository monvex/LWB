package com.example.lwb.onboarding.di

import com.example.lwb.core.presentation.snackbar.SnackbarManager
import com.example.lwb.foodDiary.data.repository.FoodDiaryRepositoryImpl
import com.example.lwb.foodDiary.domain.repository.FoodDiaryRepository
import com.example.lwb.onboarding.data.repository.OnBoardingRepositoryImpl
import com.example.lwb.onboarding.domain.repository.OnBoardingRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSnackBar(): SnackbarManager {
        return SnackbarManager
    }

}