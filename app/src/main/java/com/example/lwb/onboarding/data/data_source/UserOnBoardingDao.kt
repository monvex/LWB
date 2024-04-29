package com.example.lwb.onboarding.data.data_source

import com.example.lwb.core.presentation.googleAuth.UserData

// TODO: добавить аннотации для бд
interface UserOnBoardingDao {
    fun submitUserData(userData: UserData)
}