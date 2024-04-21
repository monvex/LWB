package com.example.lwb.core.data.remote.dto

data class MealDto(
    val id: Int,
    val dayId: Int,
    val type: String,
    val calories: Float,
    val proteins: Float,
    val fats: Float,
    val carbs: Float,
    val productList: List<Product>
)

data class Product(
    val id: Int,
    val amount: Float
)