package com.example.lwb.onboarding.data.repository

import com.example.lwb.onboarding.data.data_source.UserOnBoardingDao
import com.example.lwb.onboarding.domain.model.OnBoardingData
import com.example.lwb.onboarding.domain.model.toUser
import com.example.lwb.onboarding.domain.repository.OnBoardingRepository
import javax.inject.Inject

class OnBoardingRepositoryImpl @Inject constructor(
    private val dao: UserOnBoardingDao
): OnBoardingRepository {
    override suspend fun submitAndInsertUserData(userdata: OnBoardingData) {
        dao.submitAndInsertUserData(userData = userdata.toUser())
    }
}