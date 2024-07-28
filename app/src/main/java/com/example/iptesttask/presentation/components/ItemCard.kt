package com.example.iptesttask.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iptesttask.data.model.Item
import com.example.iptesttask.presentation.MainEvent
import com.example.iptesttask.utils.formatDate

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ItemCard(item: Item, onEvent: (MainEvent) -> Unit) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }

    if (showDeleteDialog) {
        DeleteDialog(item = item, onEvent = onEvent, onDismiss = { showDeleteDialog = false })
    }

    if (showEditDialog) {
        EditDialog(
            item = item,
            onEvent = onEvent,
            onDismiss = { showEditDialog = false }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors().copy(containerColor = Color.White)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 7.dp)
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                text = item.name,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight(500),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            IconButton(onClick = { showEditDialog = true }) {
                Icon(Icons.Default.Edit, contentDescription = null, tint = Color.Blue)
            }
            IconButton(onClick = { showDeleteDialog = true }) {
                Icon(Icons.Default.Delete, contentDescription = null, tint = Color.Red)
            }
        }
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            maxItemsInEachRow = 4,
            modifier = Modifier.padding(top = 5.dp, start = 7.dp)
        ) {
            item.tags.forEach { tag ->
                SuggestionChip(onClick = { /*TODO*/ }, label = { Text(text = tag, color = Color.Black) })
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 7.dp, end = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "На складе",
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight(500)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${item.amount}",
                    fontSize = 15.sp,
                    fontWeight = FontWeight(500)
                )
            }
            Column {
                Text(
                    text = "Дата добавления",
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight(500)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.time.formatDate(),
                    fontSize = 15.sp,
                    fontWeight = FontWeight(500)
                )
            }
        }
    }
}