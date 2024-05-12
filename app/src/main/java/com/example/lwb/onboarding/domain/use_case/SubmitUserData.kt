package com.example.lwb.onboarding.domain.use_case

import com.example.lwb.onboarding.domain.model.OnBoardingData
import com.example.lwb.onboarding.domain.repository.OnBoardingRepository

class SubmitUserData(
    private val repository: OnBoardingRepository
) {
    suspend operator fun invoke(userData: OnBoardingData) {
        repository.submitAndInsertUserData(userData)
    }
}