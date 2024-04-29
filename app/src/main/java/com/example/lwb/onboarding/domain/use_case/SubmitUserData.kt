package com.example.lwb.onboarding.domain.use_case

import com.example.lwb.core.presentation.googleAuth.UserData
import com.example.lwb.onboarding.domain.repository.OnBoardingRepository

class SubmitUserData(
    private val repository: OnBoardingRepository
) {
    suspend operator fun invoke(userData: UserData) {
        repository.submitUserData(userData)
    }
}