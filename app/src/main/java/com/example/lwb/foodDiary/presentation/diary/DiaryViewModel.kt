package com.example.lwb.foodDiary.presentation.diary

import androidx.compose.runtime.mutableStateOf
import com.example.lwb.core.presentation.LWBViewModel
import com.example.lwb.core.presentation.googleAuth.Today
import com.example.lwb.core.presentation.googleAuth.UserData
import com.example.lwb.foodDiary.domain.repository.FoodDiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
): LWBViewModel() {
    var todayData = mutableStateOf(Today())
}