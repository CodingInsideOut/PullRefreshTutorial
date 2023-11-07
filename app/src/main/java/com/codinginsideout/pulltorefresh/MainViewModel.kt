package com.codinginsideout.pulltorefresh

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var isRefreshing: Boolean by mutableStateOf(false)
        private set

    private val _items: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    val items: Flow<List<String>> = _items.asStateFlow()

    init {
        getItems()
    }

    fun getItems() {
        viewModelScope.launch {
            isRefreshing = true
            /*
             Here you would get data from an api or database
             but for this example we will just generate some random data
             */
            val items = (0..100).shuffled().map { "Item $it" }
            delay(1000) // simulate network
            _items.value = items
            isRefreshing = false
        }
    }
}