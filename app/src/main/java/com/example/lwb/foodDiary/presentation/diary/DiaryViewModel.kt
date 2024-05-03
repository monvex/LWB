package com.example.lwb.foodDiary.presentation.diary

import androidx.compose.runtime.mutableStateOf
import com.example.lwb.core.presentation.LWBViewModel
import com.example.lwb.core.presentation.googleAuth.UserData
import com.example.lwb.foodDiary.domain.model.Today
import com.example.lwb.foodDiary.domain.repository.FoodDiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
): LWBViewModel() {
    var todayData = mutableStateOf(Today())
    var writtenConsumedWater = mutableStateOf(String)

    fun onConsumedWaterChange(newValue: Int) {
        todayData.value = todayData.value.copy(consumedWater = newValue)
    }

    fun checkFloat(value: String): Boolean{
        for (i in  1..value.length - 2)
            if (!((value[i].isDigit()) or (value[i] == '.') or (value[i] == ','))){
                return false
            }
        if (!value[value.length - 1].isDigit()){
            return false
        }
        return true
    }
}