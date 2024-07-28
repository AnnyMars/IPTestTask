package com.example.iptesttask.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.iptesttask.data.model.Item
import com.example.iptesttask.presentation.MainEvent
import kotlinx.coroutines.launch

@Composable
fun EditDialog(
    item: Item,
    onEvent: (MainEvent) -> Unit,
    onDismiss: () -> Unit)
{
    var amount by remember { mutableIntStateOf(item.amount) }

    val coroutineScope = rememberCoroutineScope()

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Column (modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                Icon(imageVector = Icons.Default.Settings, contentDescription = "", tint = Color.Gray)
                Text("Количество товара")
            }

        },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    IconButton(onClick = {
                        if (amount > 0) amount -= 1
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Remove,
                            contentDescription = "Уменьшить количество"
                        )
                    }
                    Text(text = amount.toString())
                    IconButton(onClick = { amount += 1 }) {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = "Увеличить количество"
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                coroutineScope.launch {
                    val updatedItem = item.copy(amount = amount)
                    onEvent(MainEvent.UpdateItem(updatedItem))
                }
                onDismiss()
            }) {
                Text("Принять")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Отмена")
            }
        }
    )
}