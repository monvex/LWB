package com.example.lwb.foodDiary.presentation.diary

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lwb.core.data.dao.DayDao
import com.example.lwb.core.data.dao.ExerciseDao
import com.example.lwb.core.data.dao.MealDao
import com.example.lwb.core.data.dao.UserDao
import com.example.lwb.core.data.entities.Day
import com.example.lwb.core.data.entities.Exercise
import com.example.lwb.core.data.entities.Product
import com.example.lwb.core.presentation.LWBViewModel
import com.example.lwb.foodDiary.domain.model.Today
import com.example.lwb.foodDiary.domain.repository.FoodDiaryRepository
import com.example.lwb.foodDiary.presentation.diary.components.FoodAlgoritms
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class
DiaryViewModel @Inject constructor(
    private val dayDao: DayDao,
    private val userDao: UserDao,
    private val mealDao: MealDao,
    private val foodAlgoritms: FoodAlgoritms
): LWBViewModel() {
    val today = mutableStateOf(Day(LocalDate.now().toString()))

    private val _meals = MutableStateFlow<MutableList<MutableList<String>>>(mutableListOf())
    val meals: StateFlow<MutableList<MutableList<String>>> = _meals

    init {
        viewModelScope.launch{
            searchToday()
            getMeals()
        }
    }

    fun onConsumedWaterChange(newValue: Int) {
        today.value = today.value.copy(waterIntake = newValue)
        viewModelScope.launch{
            dayDao.update(today.value)
        }
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

    private suspend fun getMeals() {
        var meals: MutableList<MutableList<String>> = mutableListOf()
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH) + 1
        val day = c.get(Calendar.DAY_OF_MONTH)
        val todayMeals = mealDao.getByDate("$day-$month-$year")
        for (meal in todayMeals) {
            var currentMeal = mutableListOf(meal.type, meal.products)
            meals.add(currentMeal)
        }

        _meals.value = mutableListOf<MutableList<String>>().apply {
            for (i in meals){
                add(i)
            }
        }
    }
}