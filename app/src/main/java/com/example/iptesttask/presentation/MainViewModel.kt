package com.example.iptesttask.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iptesttask.data.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ItemRepository
) : ViewModel() {

    private var _state = MutableStateFlow(MainState())
    var state: StateFlow<MainState> = _state

    init {
        onEvent(MainEvent.LoadItems)
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.UpdateSearchQuery -> {
                _state.value = _state.value.copy(searchQuery = event.searchQuery)
                searchItems()
            }

            is MainEvent.DeleteItem -> {
                viewModelScope.launch(Dispatchers.IO){
                    repository.deleteItem(event.item)
                    onEvent(MainEvent.LoadItems)
                }

            }
            is MainEvent.UpdateItem -> {
                viewModelScope.launch(Dispatchers.IO){
                    repository.updateItem(event.item)
                    onEvent(MainEvent.LoadItems)
                }
            }

            MainEvent.LoadItems -> {
                viewModelScope.launch(Dispatchers.IO){
                    repository.getAllItems().collect { items ->
                        _state.value = _state.value.copy(items = items)
                    }

                }
            }
        }
    }

    private fun searchItems() = viewModelScope.launch(Dispatchers.IO) {
        repository.searchItems(_state.value.searchQuery).collect{items ->
            _state.value = _state.value.copy(items = items)
        }
    }
}