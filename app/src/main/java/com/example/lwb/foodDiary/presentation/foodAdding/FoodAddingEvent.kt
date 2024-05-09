package com.example.lwb.foodDiary.presentation.foodAdding

import com.example.lwb.core.data.entities.Product

sealed class FoodAddingEvent {
    data class OnSearchQueryChange(val query: String) : FoodAddingEvent()
    data class OnProductClick(val product: Product, val productWeight: Int) : FoodAddingEvent()
    data class OnProductDelete(val product: Product): FoodAddingEvent()

    data class OnChooseEating(val meal: String) : FoodAddingEvent()

    data class OnSaveEating(val meal: String): FoodAddingEvent()
}