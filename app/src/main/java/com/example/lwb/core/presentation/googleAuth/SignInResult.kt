package com.example.lwb.core.presentation.googleAuth

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String = "",
    val gender: String? = "",
    val age: Int? = 0,
    val height: Int? = 0,
    val weight: Int? = 0,
    val desiredWeight: Int? = 0
)

data class Today(
    val date: String = "",
    val userWeight: Int? = 0,
    val consumedWater: Float = 2.8f,
    val consumedCalories: Int = 1000,
    val consumedProteins: Float = 0.0f,
    val consumedFats: Float = 0.0f,
    val consumedCarbohydrates: Float = 0.0f,
    val recommenderWater: Float = 2.0f,
    val recommendedCalories: Int = 1800,
    val recommendedProteins: Float = 0.0f,
    val recommendedFats: Float = 0.0f,
    val recommendedCarbohydrates: Float = 0.0f
)