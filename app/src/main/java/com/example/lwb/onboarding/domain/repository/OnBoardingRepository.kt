package com.example.lwb.onboarding.domain.repository

import com.example.lwb.core.presentation.googleAuth.UserData

interface OnBoardingRepository {
    suspend fun submitUserData(userdata: UserData)
}