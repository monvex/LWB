package com.example.lwb.exerciseBase.presentation.exercisePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lwb.core.data.dao.ExerciseDao
import com.example.lwb.core.data.entities.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ExercisePageViewModel @Inject constructor(
    private val exerciseDao: ExerciseDao
) : ViewModel() {

    private val _state = MutableStateFlow(ExercisePageState())
    val state: StateFlow<ExercisePageState> = _state

    private val muscleGroups = listOf("Плечи", "Ноги", "Спина", "Грудь", "Пресс", "Руки")

    init {
        _state.value = _state.value.copy(
            muscleGroups = muscleGroups
        )
    }

    private val _searchResults = MutableStateFlow<List<Exercise>>(emptyList())
    val searchResults: StateFlow<List<Exercise>> = _searchResults

    fun onEvent(event: ExercisePageEvent) {
        viewModelScope.launch {
            handleEvent(event)
        }
    }

    private suspend fun handleEvent(event: ExercisePageEvent) {
        when (event) {
            is ExercisePageEvent.OnSearchQueryChange -> {
                _state.value = _state.value.copy(
                    searchQuery = event.query
                )
                if (event.query.isNotEmpty()) {
                    searchExercises()
                }
            }

            is ExercisePageEvent.OnMuscleGroupClick -> {
                // TODO: Navigate to ExerciseListScreen with muscleGroup
            }
        }
    }

    private suspend fun searchExercises() {
        _searchResults.value = exerciseDao.getAll()
    }
}