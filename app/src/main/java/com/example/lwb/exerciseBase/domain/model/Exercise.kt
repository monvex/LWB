package com.example.lwb.exerciseBase.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
*   Класс, описывающий упражнение
*/
@Entity
data class Exercise(
    @PrimaryKey
    val test: String
)
