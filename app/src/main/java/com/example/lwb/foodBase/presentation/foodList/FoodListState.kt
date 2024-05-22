package com.example.lwb.foodBase.presentation.foodList

import com.example.lwb.core.data.entities.Product

data class FoodListState(
    val searchQuery: String = "",
    val allProducts: List<Product> = emptyList()
)