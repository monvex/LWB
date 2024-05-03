package com.example.lwb.foodDiary.domain.model

data class Today(
    val date: String = "",
    val userWeight: Int? = 0,
    val consumedWater: Int = 200,
    val consumedCalories: Int = 1000,
    val consumedProteins: Float = 0.0f,
    val consumedFats: Float = 0.0f,
    val consumedCarbohydrates: Float = 0.0f,
    val recommenderWater: Int = 2000,
    val recommendedCalories: Int = 1800,
    val recommendedProteins: Float = 0.0f,
    val recommendedFats: Float = 0.0f,
    val recommendedCarbohydrates: Float = 0.0f
)
