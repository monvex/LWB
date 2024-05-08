package com.example.lwb.foodDiary.presentation.foodAdding

import com.example.lwb.core.data.entities.Product

data class FoodAddingState(
    val searchQuery: String = "",
    val chosenProducts: MutableList<Product> = mutableListOf(),
    val eating: String = "Перекус"
)
