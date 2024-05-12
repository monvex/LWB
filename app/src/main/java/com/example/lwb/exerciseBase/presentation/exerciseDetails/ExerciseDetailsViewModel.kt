package com.example.lwb.exerciseBase.presentation.exerciseDetails

import androidx.lifecycle.ViewModel
import com.example.lwb.core.data.dao.ExerciseDao
import com.example.lwb.core.data.entities.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExerciseDetailsViewModel @Inject constructor(
    private val exerciseDao: ExerciseDao
) : ViewModel() {
    suspend fun getExerciseById(id: Int): Exercise? {
        return exerciseDao.getById(id)
    }
}