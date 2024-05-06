package com.example.lwb.core.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    foreignKeys = [ForeignKey(entity = Day::class, parentColumns = ["date"], childColumns = ["dayId"], onDelete = ForeignKey.CASCADE)],
    indices = [Index("dayId")]
)
data class Meal(
    @PrimaryKey val id: Int,
    val dayId: String,
    val type: String,
    val name: String,
    val calories: Int,
    val proteins: Int,
    val fats: Int,
    val carbohydrates: Int,
    val products: String
)