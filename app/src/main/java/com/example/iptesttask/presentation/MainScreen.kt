package com.example.iptesttask.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iptesttask.presentation.components.ItemCard
import com.example.iptesttask.presentation.components.SearchBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    state: MainState,
    onEvent: (MainEvent) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = Color(0xFFf5f4f5))
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Список товаров",
                    color = Color.Black,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Color(0xFFb1dcfc))
        )

        Spacer(modifier = Modifier.height(10.dp))
        
        SearchBar(
            query = state.searchQuery,
            onQueryChange = { onEvent(MainEvent.UpdateSearchQuery(it)) })

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.items) { item ->
                ItemCard(item = item, onEvent = onEvent)
            }
        }

    }
}