package com.example.lwb.onboarding.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.lwb.core.presentation.LWBViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
):  LWBViewModel() {
    private val _userData = mutableStateOf(OnBoardingState())
    val userData: State<OnBoardingState> = _userData

}