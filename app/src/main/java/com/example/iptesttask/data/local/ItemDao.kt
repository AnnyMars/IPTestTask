package com.example.iptesttask.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.example.iptesttask.data.model.Item

@Dao
interface ItemDao {
    @Query("SELECT * FROM item")
    fun getAllItems(): List<Item>

    @Query("SELECT * FROM item WHERE name LIKE :searchQuery")
    fun searchItems(searchQuery: String): List<Item>

    @Update
    suspend fun updateItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)
}