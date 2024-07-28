package com.example.iptesttask.utils

import java.util.Date
import java.text.SimpleDateFormat
import java.util.Locale

fun Long.formatDate(): String{
    val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    val date = Date(this)
    return sdf.format(date)
}

