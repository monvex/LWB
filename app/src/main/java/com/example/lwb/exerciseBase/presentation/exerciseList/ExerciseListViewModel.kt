package com.example.lwb.exerciseBase.presentation.exerciseList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lwb.core.data.dao.ExerciseDao
import com.example.lwb.core.data.entities.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    private val exerciseDao: ExerciseDao
) : ViewModel() {

    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises

    fun loadExercises(muscleGroup: String) {
        viewModelScope.launch {
            _exercises.value = exerciseDao.getByMuscleGroup(muscleGroup)
        }
    }
}