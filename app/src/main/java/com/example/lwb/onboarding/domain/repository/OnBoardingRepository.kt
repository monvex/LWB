package com.example.lwb.onboarding.domain.repository

import com.example.lwb.onboarding.domain.model.OnBoardingData

interface OnBoardingRepository {
    suspend fun submitAndInsertUserData(userdata: OnBoardingData)
}