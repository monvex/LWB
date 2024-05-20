package com.example.lwb.settings.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.lwb.core.data.dao.DayDao
import com.example.lwb.core.data.dao.UserDao
import com.example.lwb.core.data.entities.Day
import com.example.lwb.core.data.entities.User
import com.example.lwb.core.presentation.LWBViewModel
import com.example.lwb.core.presentation.googleAuth.GoogleAuthUiClient
import com.example.lwb.core.presentation.googleAuth.UserData
import com.example.lwb.foodDiary.presentation.diary.components.FoodAlgoritms
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Calendar
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userDao: UserDao,
    private val dayDao: DayDao,
    private val foodAlgoritms: FoodAlgoritms
) : LWBViewModel() {

    var user = mutableStateOf(User())
    val today = mutableStateOf(Day(LocalDate.now().toString()))

    init{
        viewModelScope.launch{
            searchUser()
            searchToday()
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
        today.value = today.value.copy(weight = newValue)
        viewModelScope.launch{
            userDao.update(user.value)
            dayDao.update(today.value)
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

    private suspend fun searchToday() {
        val c = Calendar.getInstance()

        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH) + 1
        val day = c.get(Calendar.DAY_OF_MONTH)
        val searchResult = dayDao.getByDate("$day-$month-$year")
        if (searchResult.isNotEmpty()){
            today.value = searchResult[0]
        }
        else{
            val user = userDao.getAll()[0]
            today.value = Day("$day-$month-$year", user.weight, 0, 0, 0, 0, 0, foodAlgoritms.calculateWater(user), foodAlgoritms.calculateCalories(user), foodAlgoritms.calculateProteins(user), foodAlgoritms.calculateFats(user), foodAlgoritms.calculateCarbohydrates(user))
            dayDao.insert(today.value)
        }
    }
}