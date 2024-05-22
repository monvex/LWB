package com.example.lwb.foodBase.presentation.foodDetails

import androidx.lifecycle.ViewModel
import com.example.lwb.core.data.dao.ProductDao
import com.example.lwb.core.data.entities.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodDetailsViewModel @Inject constructor(
    private val productDao: ProductDao
) : ViewModel() {
    suspend fun getFoodById(id: Int): Product? {
        return productDao.getById(id).firstOrNull()
    }

}