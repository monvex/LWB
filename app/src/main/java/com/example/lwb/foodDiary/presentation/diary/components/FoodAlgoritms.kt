package com.example.lwb.foodDiary.presentation.diary.components

import com.example.lwb.core.data.entities.User

class FoodAlgoritms(){
    fun calculateWater(user: User): Int{
        return if (user.gender == "М") 35 * user.weight else 31 * user.weight
    }

    fun calculateCalories(user: User): Int{
        val base = 6.25 * user.height + 10 * user.weight - 4.92 * user.age
        return if (user.gender == "М"){
            base.toInt() + 5
        } else{
            base.toInt() - 161
        }
    }

    fun calculateProteins(user: User): Int{
        return (1.2 * user.weight).toInt()
    }

    fun calculateFats(user: User): Int{
        val base = 6.25 * user.height + 10 * user.weight - 4.92 * user.age
        return if (user.gender == "М"){
            ((base + 5) * 0.2).toInt()
        } else{
            ((base - 161) * 0.2).toInt()
        }
    }

    fun calculateCarbohydrates(user: User): Int {
        val base = 6.25 * user.height + 10 * user.weight - 4.92 * user.age
        return if (user.gender == "М") {
            ((base + 5) * 0.5).toInt()
        } else {
            ((base - 161) * 0.5).toInt()
        }
    }
}