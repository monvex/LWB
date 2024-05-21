package com.example.lwb.onboarding.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.lwb.core.data.dao.UserDao
import com.example.lwb.core.data.entities.User
import com.example.lwb.core.presentation.LWBViewModel
import com.example.lwb.core.presentation.snackbar.SnackbarManager
import com.example.lwb.core.presentation.snackbar.SnackbarMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    val userDao: UserDao
):  LWBViewModel() {
    private val _userData = mutableStateOf(OnBoardingState(gender = "М", age = 0, height = 0, weight = 0, desiredWeight = 0))
    val userData: State<OnBoardingState> = _userData

    fun onGenderChange(gender: String) {
        _userData.value = _userData.value.copy(gender = gender)
    }

    fun onAgeChange(age: Int) {
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

    fun saveUser() {
        runBlocking {
            userDao.insert(User(1, userData.value.gender, userData.value.age, userData.value.height, userData.value.weight, userData.value.desiredWeight))
        }
    }


}