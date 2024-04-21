package com.example.lwb.core.data.remote.dto

data class ProductDto(
    val id: Int,
    val label: String,
    val calories: Float,
    val proteins: Float,
    val fat: Float,
    val carbs: Float,
    val image: String? // Base64 encoded string
)