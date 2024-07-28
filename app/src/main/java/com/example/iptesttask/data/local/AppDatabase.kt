package com.example.iptesttask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.iptesttask.data.model.Converters
import com.example.iptesttask.data.model.Item

@Database(entities = [Item::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemDao
}