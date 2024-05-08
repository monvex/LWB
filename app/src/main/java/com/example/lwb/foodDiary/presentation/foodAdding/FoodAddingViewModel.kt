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
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    init {
        viewModelScope.launch {
            searchProducts()
        }
    }

    private suspend fun searchProducts() {
        _products.value = productDao.getAll()
    }
}