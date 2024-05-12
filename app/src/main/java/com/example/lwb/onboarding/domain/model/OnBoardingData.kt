package com.example.lwb.onboarding.domain.model

import com.example.lwb.core.data.entities.User

data class OnBoardingData(
    val gender: String? = null,
    val age: Int? = null,
    val height: Int? = null,
    val weight: Int? = null,
    val desiredWeight: Int? = null
)


fun OnBoardingData.toUser(): User {
    return User(
        id = 0 ,
        gender = gender ?: "none" ,
        age = age ?: 0,
        height = height ?: 0 ,
        weight = weight ?: 0,
        desiredWeight = desiredWeight ?: 0
    )
}