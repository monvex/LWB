package com.example.lwb.settings.presentation

import androidx.compose.runtime.mutableStateOf
import com.example.lwb.core.presentation.LWBViewModel
import com.example.lwb.core.presentation.googleAuth.GoogleAuthUiClient
import com.example.lwb.core.presentation.googleAuth.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
) : LWBViewModel() {

    var userData = mutableStateOf(UserData())

    fun onHeightChange(newValue: Int) {
        userData.value = userData.value.copy(height = newValue)
    }
    fun onAgeChange(newValue: Int) {
        userData.value = userData.value.copy(age = newValue)
    }

    fun onGenderChange(newValue: String) {
        userData.value = userData.value.copy(gender = newValue)
    }

    fun onWeightChange(newValue: Int) {
        userData.value = userData.value.copy(weight = newValue)
    }

    fun onDesiredWeightChange(newValue: Int) {
        userData.value = userData.value.copy(targetWeight = newValue)
    }
}