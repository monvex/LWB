package com.example.lwb.foodDiary.presentation.foodAdding

import com.example.lwb.core.data.entities.Product

data class FoodAddingState(
    val searchQuery: String = "",
    val chosenProducts: MutableMap<Product, Int> = mutableMapOf(),
    val eating: String = "Перекус"
)
