package com.example.lwb.onboarding.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.lwb.core.presentation.LWBViewModel
import com.example.lwb.core.presentation.snackbar.SnackbarManager
import com.example.lwb.core.presentation.snackbar.SnackbarMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    snackbar: SnackbarManager
):  LWBViewModel() {
    private val _userData = mutableStateOf(OnBoardingState())
    val userData: State<OnBoardingState> = _userData

    fun onGenderChange(gender: String) {
        _userData.value = _userData.value.copy(gender = gender)
    }

    fun onAgeChange(age: Int?) {
        _userData.value = _userData.value.copy(age = age)
    }

    fun onHeightChange(height: Int) {
        _userData.value = _userData.value.copy(height = height)
    }

    fun onWeightChange(weight: Int) {
        _userData.value = _userData.value.copy(weight = weight)
    }

    fun onDesiredWeightChange(desiredWeight: Int) {
        _userData.value = _userData.value.copy(desiredWeight = desiredWeight)
    }

    fun showToast(msg: String) {
        launchCatching {
            SnackbarManager.showMessage(SnackbarMessage.StringSnackbar(msg))
        }
    }


}