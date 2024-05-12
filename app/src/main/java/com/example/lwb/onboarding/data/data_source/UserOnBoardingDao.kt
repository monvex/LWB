package com.example.lwb.onboarding.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import com.example.lwb.core.data.entities.User
import com.example.lwb.core.presentation.googleAuth.UserData

// TODO: добавить аннотации для бд
@Dao
interface UserOnBoardingDao {
    @Insert
    fun submitAndInsertUserData(userData: User)
}