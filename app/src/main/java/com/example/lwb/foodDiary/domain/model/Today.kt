package com.example.lwb.foodDiary.domain.model

data class Today(
    val date: String = "",
    val userWeight: Int? = 0,
    val waterIntake: Int = 0,
    val caloriesIntake: Int = 0,
    val proteinsIntake: Int = 0,
    val fatsIntake: Int = 0,
    val carbsIntake: Int = 0,
    val recommenderWater: Int = 0,
    val recommendedCalories: Int = 0,
    val recommendedProteins: Int = 0,
    val recommendedFats: Int = 0,
    val recommendedCarbs: Int = 0
)
