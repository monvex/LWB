package com.example.lwb.onboarding.data.repository

import com.example.lwb.core.presentation.googleAuth.UserData
import com.example.lwb.onboarding.data.data_source.UserOnBoardingDao
import com.example.lwb.onboarding.domain.repository.OnBoardingRepository

class OnBoardingRepositoryImpl(
    private val dao: UserOnBoardingDao
): OnBoardingRepository {
    override suspend fun submitUserData(userdata: UserData) {
        TODO("Not yet implemented")
    }
}