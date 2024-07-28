package com.example.iptesttask.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.iptesttask.data.model.Item
import com.example.iptesttask.presentation.MainEvent
import kotlinx.coroutines.launch

@Composable
fun DeleteDialog(
    item: Item,
    onEvent: (MainEvent) -> Unit,
    onDismiss: () -> Unit
){

    val coroutineScope = rememberCoroutineScope()

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Column (modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                Icon(imageVector = Icons.Default.Warning, contentDescription = "", tint = Color.Gray)
                Text("Удаление товара")
            }

        },
        text = { Text("Вы действительно хотите удалить выбранный товар?") },
        confirmButton = {
            TextButton(onClick = {
                coroutineScope.launch {
                    onEvent(MainEvent.DeleteItem(item))
                }
                onDismiss()
            }) {
                Text("Да")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss()}) {
                Text("Нет")
            }
        }
    )
}