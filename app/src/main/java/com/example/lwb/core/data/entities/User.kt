package com.example.lwb.core.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id: Int,
    val googleId: String,
    val gender: String,
    val age: Int,
    val height: Int,
    val currentWeight: Int,
    val desiredWeight: Int
)