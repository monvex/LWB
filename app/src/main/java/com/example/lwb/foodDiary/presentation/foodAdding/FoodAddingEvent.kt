package com.example.lwb.foodDiary.presentation.foodAdding

import com.example.lwb.core.data.entities.Product

sealed class FoodAddingEvent {
    data class OnSearchQueryChange(val query: String) : FoodAddingEvent()
    data class OnProductClick(val product: Product) : FoodAddingEvent()

    data class OnChooseEating(val meal: String) : FoodAddingEvent()
}