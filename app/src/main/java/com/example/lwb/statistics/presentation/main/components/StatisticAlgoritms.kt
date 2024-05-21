package com.example.lwb.statistics.presentation.main.components

import com.example.lwb.core.data.entities.Day
import java.lang.Math.abs

class StatisticAlgoritms(){
    fun examEating(dayList: List<Day>): Float{
        var coefSum = 0f
        for (day in dayList.subList(0, if (dayList.isEmpty()) 0 else dayList.size - 1)){
            var currentCoef = kotlin.math.abs(day.recommendedCalories - day.caloriesIntake) / day.recommendedCalories.toFloat()
            coefSum += currentCoef
        }
        return coefSum / dayList.size
    }
}