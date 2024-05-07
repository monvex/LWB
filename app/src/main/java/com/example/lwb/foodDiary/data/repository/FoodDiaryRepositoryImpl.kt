package com.example.lwb.foodDiary.data.repository

import com.example.lwb.core.data.LWBDatabase
import com.example.lwb.core.presentation.googleAuth.UserData
import com.example.lwb.foodDiary.data.remote.Api
import com.example.lwb.foodDiary.domain.model.Product
import com.example.lwb.foodDiary.domain.model.ProductDetails
import com.example.lwb.foodDiary.domain.model.Today
import com.example.lwb.foodDiary.domain.repository.FoodDiaryRepository
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement
import java.util.Calendar
import javax.inject.Inject

class FoodDiaryRepositoryImpl @Inject constructor(
    private val api: Api
): FoodDiaryRepository {
    override suspend fun getProductsByName(productName: String): List<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun getProductDetailsByName(productName: String): ProductDetails {
        TODO("Not yet implemented")
    }

    override suspend fun addProductToDiary(product: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun addWaterToDiary(volume: Double) {
        TODO("Not yet implemented")
    }

    override suspend fun getToday(): Today {
        TODO("Not yet implemented")
    }
}