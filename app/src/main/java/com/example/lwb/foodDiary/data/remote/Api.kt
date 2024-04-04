package com.example.lwb.foodDiary.data.remote

import com.example.lwb.foodDiary.data.remote.dto.ProductDetailsDto
import com.example.lwb.foodDiary.data.remote.dto.ProductDto
import com.example.lwb.foodDiary.domain.model.Product
import com.example.lwb.foodDiary.domain.model.ProductDetails

//todo: Изменить название файла под название api, которое мы будем юзать
//todo: Соответственно поменять dto файлы под api, сейчас пока навскидку файлы написаны

interface Api {
    suspend fun getProductsByName(productName: String): List<ProductDto>

    suspend fun getProductDetailsByName(productName: String): ProductDetailsDto
}