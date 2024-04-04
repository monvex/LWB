package com.example.lwb.foodDiary.domain.repository

import com.example.lwb.foodDiary.domain.model.Product
import com.example.lwb.foodDiary.domain.model.ProductDetails

interface FoodDiaryRepository {
    suspend fun getProductsByName(productName: String): List<Product>

    suspend fun getProductDetailsByName(productName: String): ProductDetails

    suspend fun addProductToDiary(product: Product)

    suspend fun addWaterToDiary(volume: Double)
}