package com.example.lwb.onboarding.presentation

data class OnBoardingState(
    val gender: String? = null,
    val age: Int? = null,
    val height: Int? = null,
    val currentUserWeight: Double? = null,
    val goalUserWeight: Double? = null
)
