package com.example.lwb.foodDiary.presentation.diary

import com.example.lwb.core.presentation.LWBViewModel
import com.example.lwb.foodDiary.domain.repository.FoodDiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val repository: FoodDiaryRepository
): LWBViewModel() {
}