package com.example.lwb.foodDiary.presentation.foodAdding

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.lwb.core.data.dao.DayDao
import com.example.lwb.core.data.dao.MealDao
import com.example.lwb.core.data.dao.ProductDao
import com.example.lwb.core.data.dao.UserDao
import com.example.lwb.core.data.entities.Day
import com.example.lwb.core.data.entities.Exercise
import com.example.lwb.core.data.entities.Meal
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
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class
FoodAddingViewModel @Inject constructor(
    private val productDao: ProductDao,
    private val dayDao: DayDao,
    private val mealDao: MealDao
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
                _state.value = _state.value.copy(
                    chosenProducts = mutableMapOf<Product, Int>().apply {
                        for ((i, j) in _state.value.chosenProducts.entries){
                            put(i, j)
                        }
                        put(event.product, event.productWeight)
                    }
                )
            }

            is FoodAddingEvent.OnProductDelete -> {
                _state.value = _state.value.copy(
                    chosenProducts = mutableMapOf<Product, Int>().apply {
                        for ((i, j) in _state.value.chosenProducts.entries){
                            put(i, j)
                        }
                        remove(event.product)
                    }
                )
            }

            is FoodAddingEvent.OnChooseEating -> {
                _state.value = _state.value.copy(eating = event.meal)
            }

            is FoodAddingEvent.OnSaveEating -> {
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH) + 1
                val day = c.get(Calendar.DAY_OF_MONTH)
                val id = "${year}_${month}_${day}_${(0..1000000).random()}"
                var calories = 0
                var proteins = 0
                var fats = 0
                var carbohydrates = 0
                var stringProducts = ""
                for ((i, j) in _state.value.chosenProducts.entries){
                    calories += i.calories * j / 100
                    proteins += i.proteins * j / 100
                    fats += i.fats * j / 100
                    carbohydrates += i.carbohydrates * j / 100
                    stringProducts += "${i.name}(${_state.value.chosenProducts[i]}г.), "
                }
                val meal = Meal(id, "$day-$month-$year", _state.value.eating, calories, proteins, fats, carbohydrates, stringProducts.substring(0, stringProducts.length-2))
                saveMeal(meal, "$day-$month-$year")
            }
        }
    }

    private suspend fun searchProducts() {
        _products.value = productDao.getAll()
    }

    private suspend fun searchProductsByName(name: String) {
        _productsByName.value = productDao.getByName("$name%")
    }

    private suspend fun saveMeal(meal: Meal, date: String){
        val today = dayDao.getByDate(date)[0]
        val newDay = Day(today.date, today.weight, today.waterIntake, today.caloriesIntake + meal.calories, today.proteinsIntake + meal.proteins, today.fatsIntake + meal.fats, today.carbohydratesIntake + meal.carbohydrates, today.recommendedWater, today.recommendedCalories, today.recommendedProteins, today.recommendedFats, today.recommendedCarbohydrates)
        dayDao.update(newDay)
        mealDao.insert(meal)
    }
}