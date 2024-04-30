package com.example.lwb.foodDiary.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey
    val test: String
)
