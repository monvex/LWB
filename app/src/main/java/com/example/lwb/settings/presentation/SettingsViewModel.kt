package com.example.lwb.settings.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.lwb.core.data.dao.UserDao
import com.example.lwb.core.data.entities.User
import com.example.lwb.core.presentation.LWBViewModel
import com.example.lwb.core.presentation.googleAuth.GoogleAuthUiClient
import com.example.lwb.core.presentation.googleAuth.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userDao: UserDao
) : LWBViewModel() {

    var user = mutableStateOf(User())

    init{
        viewModelScope.launch{
            searchUser()
        }
    }
    fun onHeightChange(newValue: Int) {
        user.value = user.value.copy(height = newValue)
        viewModelScope.launch{
            userDao.update(user.value)
        }
    }
    fun onAgeChange(newValue: Int) {
        user.value = user.value.copy(age = newValue)
        viewModelScope.launch{
            userDao.update(user.value)
        }
    }

    fun onGenderChange(newValue: String) {
        user.value = user.value.copy(gender = newValue)
        viewModelScope.launch{
            userDao.update(user.value)
        }
    }

    fun onWeightChange(newValue: Int) {
        user.value = user.value.copy(weight = newValue)
        viewModelScope.launch{
            userDao.update(user.value)
        }
    }

    fun onDesiredWeightChange(newValue: Int) {
        user.value = user.value.copy(desiredWeight = newValue)
        viewModelScope.launch{
            userDao.update(user.value)
        }
    }

    suspend fun searchUser(){
        user.value = userDao.getAll()[0]
    }
}