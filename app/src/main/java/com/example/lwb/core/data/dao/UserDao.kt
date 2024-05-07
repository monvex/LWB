package com.example.lwb.core.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.lwb.core.data.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    suspend fun getAll(): List<User>

    @Insert
    fun insert(user: User)
    @Update
    suspend fun update(vararg user: User)

    @Delete
    fun delete(user: User)
}