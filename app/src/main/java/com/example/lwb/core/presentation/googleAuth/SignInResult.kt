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