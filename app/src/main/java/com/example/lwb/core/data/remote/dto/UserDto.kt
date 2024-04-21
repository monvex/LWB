package com.example.lwb.core.data.remote.dto

data class UserDto(
    val id: Int,
    val googleId: String,
    val gender: String,
    val age: Int,
    val height: Float,
    val currentWeight: Float,
    val targetWeight: Float,
    val target: String
)