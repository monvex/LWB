package com.example.lwb.onboarding.data.data_source

import androidx.room.Dao
import com.example.lwb.core.presentation.googleAuth.UserData

// TODO: добавить аннотации для бд
@Dao
interface UserOnBoardingDao {
    fun submitUserData(userData: UserData)
}