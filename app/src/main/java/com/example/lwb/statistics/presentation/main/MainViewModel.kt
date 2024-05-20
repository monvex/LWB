package com.example.lwb.statistics.presentation.main

import androidx.lifecycle.viewModelScope
import com.example.lwb.R
import com.example.lwb.core.data.dao.DayDao
import com.example.lwb.core.data.dao.UserDao
import com.example.lwb.core.data.entities.Day
import com.example.lwb.core.data.entities.Product
import com.example.lwb.core.presentation.LWBViewModel
import com.example.lwb.foodDiary.presentation.diary.components.FoodAlgoritms
import com.example.lwb.statistics.presentation.main.components.StatisticAlgoritms
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dayDao: DayDao,
    private val userDao: UserDao,
    private val statisticAlgoritms: StatisticAlgoritms,
    private val foodAlgoritms: FoodAlgoritms
) : LWBViewModel() {

    private val _days = MutableStateFlow<List<Day>>(emptyList())
    val days: StateFlow<List<Day>> = _days

    private val _image = MutableStateFlow<Int>(R.drawable.white)
    val image: StateFlow<Int> = _image

    private val _description = MutableStateFlow<String>("")
    val description: StateFlow<String> = _description
    init {
        viewModelScope.launch {
            getDays()
        }
    }

    suspend fun getDays(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH) + 1
        var day = c.get(Calendar.DAY_OF_MONTH) - 10
        val searchResult = dayDao.getByDate("${day+10}-$month-$year")
        if (searchResult.isEmpty()){
            val user = userDao.getAll()[0]
            val today = Day("$day-$month-$year", user.weight, 0, 0, 0, 0, 0, foodAlgoritms.calculateWater(user), foodAlgoritms.calculateCalories(user), foodAlgoritms.calculateProteins(user), foodAlgoritms.calculateFats(user), foodAlgoritms.calculateCarbohydrates(user))
            dayDao.insert(today)
        }
        for (i in 0..10){
            day += 1
            var currentDay = dayDao.getByDate("$day-$month-$year")
            if (currentDay.isNotEmpty()) {
                _days.value = mutableListOf<Day>().apply {
                    for (i in _days.value) {
                        add(i)
                    }
                    add(currentDay[0])
                }
            }
        }
        val coef = statisticAlgoritms.examEating(days.value)
        if (coef <= 0.1){
            _image.value = R.drawable.good
            _description.value = "Вы питаетесь отлично!"
        }
        else if (coef <= 0.25){
            _image.value = R.drawable.medium
            _description.value = "Вы питаетесь хорошо!"
        }
        else{
            _image.value = R.drawable.bad
            _description.value = "Вы питаетесь плохо!"
        }
    }

}
