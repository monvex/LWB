package com.example.lwb.auth.presentation.signin

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val username: String?,
    val gender: String?,
    val age: Int?,
    val height: Int?,
    val weight: Int?,
    val desiredWeight: Int?
)