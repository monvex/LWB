package com.example.lwb.foodBase.presentation.foodList

sealed class FoodListEvent {
    data class OnSearchQueryChange(val query: String) : FoodListEvent()
}