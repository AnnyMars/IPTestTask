package com.example.iptesttask.data

import com.example.iptesttask.data.local.ItemDao
import com.example.iptesttask.data.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ItemRepository @Inject constructor(
    private val itemDao: ItemDao
) {

    fun getAllItems(): Flow<List<Item>> = flow {
        emit(itemDao.getAllItems())
    }

    fun searchItems(query: String): Flow<List<Item>> = flow {
        emit(itemDao.searchItems("%$query%"))
    }

    suspend fun updateItem(item: Item) {
        itemDao.updateItem(item)
    }

    suspend fun deleteItem(item: Item) {
        itemDao.deleteItem(item)
    }

}