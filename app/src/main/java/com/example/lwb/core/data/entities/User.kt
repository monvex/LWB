package com.example.lwb.core.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id: Int = 0,
    val gender: String = "",
    val age: Int = 0,
    val height: Int = 0,
    val weight: Int = 0,
    val desiredWeight: Int = 0
)