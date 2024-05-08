package com.example.lwb.foodDiary.presentation.foodAdding

sealed class FoodAddingEvent {
    data class OnSearchQueryChange(val query: String) : FoodAddingEvent()
}