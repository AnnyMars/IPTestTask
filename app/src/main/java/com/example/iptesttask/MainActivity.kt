package com.example.iptesttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.iptesttask.presentation.MainScreen
import com.example.iptesttask.presentation.MainViewModel
import com.example.iptesttask.ui.theme.IPTestTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = hiltViewModel()
            val state = viewModel.state.collectAsState().value
            IPTestTaskTheme {
                MainScreen(state = state, onEvent = viewModel::onEvent)
            }
        }
    }
}