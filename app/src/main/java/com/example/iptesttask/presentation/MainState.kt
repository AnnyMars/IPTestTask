package com.example.iptesttask.presentation

import com.example.iptesttask.data.model.Item

data class MainState(
    val searchQuery: String = "",
    val items: List<Item> = emptyList()
)