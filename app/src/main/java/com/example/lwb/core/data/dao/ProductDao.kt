package com.example.lwb.core.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lwb.core.data.entities.Exercise
import com.example.lwb.core.data.entities.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    suspend fun getAll(): List<Product>

    @Query("SELECT * FROM Product WHERE name like :name")
    suspend fun getByName(name: String): List<Product>

    @Query("SELECT * FROM Product WHERE LOWER(name) LIKE LOWER(:name) || '%'")
    suspend fun getByNameU(name: String): List<Product>

    @Query("SELECT * FROM Product WHERE id like :id")
    suspend fun getById(id: Int): List<Product>

    @Query("SELECT * FROM Product")
    suspend fun getAllProducts(): List<Product>

    @Insert
    fun insert(product: Product)

    @Delete
    fun delete(product: Product)
}