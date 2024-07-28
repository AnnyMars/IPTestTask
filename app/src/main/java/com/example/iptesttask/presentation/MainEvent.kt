package com.example.iptesttask.presentation

import com.example.iptesttask.data.model.Item

sealed class MainEvent{
    data class UpdateSearchQuery(val searchQuery: String): MainEvent()
    data object LoadItems: MainEvent()
    data class DeleteItem(val item: Item): MainEvent()
    data class UpdateItem(val item: Item): MainEvent()
}