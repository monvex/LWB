package com.example.lwb.foodDiary.presentation.foodAdding

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.lwb.core.data.dao.DayDao
import com.example.lwb.core.data.dao.ProductDao
import com.example.lwb.core.data.dao.UserDao
import com.example.lwb.core.data.entities.Day
import com.example.lwb.core.data.entities.Exercise
import com.example.lwb.core.data.entities.Product
import com.example.lwb.core.presentation.LWBViewModel
import com.example.lwb.exerciseBase.presentation.exercisePage.ExercisePageEvent
import com.example.lwb.exerciseBase.presentation.exercisePage.ExercisePageState
import com.example.lwb.foodDiary.presentation.diary.components.FoodAlgoritms
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class
FoodAddingViewModel @Inject constructor(
    private val productDao: ProductDao,
): LWBViewModel() {
    private val _state = MutableStateFlow(FoodAddingState())
    val state: StateFlow<FoodAddingState> = _state

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _productsByName = MutableStateFlow<List<Product>>(emptyList())
    val productsByName: StateFlow<List<Product>> = _productsByName



    init {
        viewModelScope.launch {
            searchProducts()
        }
    }

    fun onEvent(event: FoodAddingEvent) {
        viewModelScope.launch {
            handleEvent(event)
        }
    }

    private suspend fun handleEvent(event: FoodAddingEvent) {
        when (event) {
            is FoodAddingEvent.OnSearchQueryChange -> {
                _state.value = _state.value.copy(
                    searchQuery = event.query
                )
                if (event.query.isNotEmpty()) {
                    searchProductsByName(event.query)
                }
            }

            is FoodAddingEvent.OnProductClick -> {
                if(_state.value.chosenProducts.contains(event.product)){
                    _state.value = _state.value.copy(
                        chosenProducts = mutableListOf<Product>().apply {
                            addAll(_state.value.chosenProducts)
                            remove(event.product)
                        }
                    )
                }
                else {
                    _state.value = _state.value.copy(
                        chosenProducts = mutableListOf<Product>().apply {
                            addAll(_state.value.chosenProducts)
                            add(event.product)
                        }
                    )
                }
            }

            is FoodAddingEvent.OnChooseEating -> {
                _state.value = _state.value.copy(eating = event.meal)
            }
        }
    }

    private suspend fun searchProducts() {
        _products.value = productDao.getAll()
    }

    private suspend fun searchProductsByName(name: String) {
        _productsByName.value = productDao.getByName("$name%")
    }
}