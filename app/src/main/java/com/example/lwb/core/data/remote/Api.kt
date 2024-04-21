package com.example.lwb.core.data.remote

import com.example.lwb.core.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    // Getting user
    @GET("user/{googleId}")
    suspend fun getUser(@Path("googleId") googleId: String): UserDto
}