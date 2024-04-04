package com.example.lwb.foodDiary.data.repository

import com.example.lwb.foodDiary.domain.model.Product
import com.example.lwb.foodDiary.domain.model.ProductDetails
import com.example.lwb.foodDiary.domain.repository.FoodDiaryRepository

class FoodDiaryRepositoryImpl: FoodDiaryRepository {
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
}