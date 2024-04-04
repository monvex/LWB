package com.example.lwb.foodDiary.data.data_source

import com.example.lwb.foodDiary.domain.model.Product
import com.example.lwb.foodDiary.domain.model.ProductDetails

//todo: сюда нужно добавить аннотации для бд, чтобы это было привязано к ней
//Здесь типа запросы к бд
interface FoodDiaryDao {
    fun getProductsByName(productName: String): List<Product>

    fun getProductDetailsByName(productName: String): ProductDetails

    fun addProductToDiary(product: Product)

    fun addWaterToDiary(volume: Double)
}