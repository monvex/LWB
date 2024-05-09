package com.example.lwb.core.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

@Entity
data class Meal(
    @PrimaryKey val id: String,
    val date: String,
    val type: String,
    val calories: Int,
    val proteins: Int,
    val fats: Int,
    val carbohydrates: Int,
    val products: String
)