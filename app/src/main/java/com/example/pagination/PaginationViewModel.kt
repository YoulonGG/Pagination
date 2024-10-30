package com.example.pagination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PaginationViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<String>>(emptyList())
    val items: StateFlow<List<String>> = _items

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val allItems = List(20) { "Item $it" }
    private var currentPage = 0
    private val pageSize = 5

    init {
        loadMoreItemsIfNeeded()
    }

    fun onScrollToEnd(visibleItemIndex: Int) {
        if (visibleItemIndex == _items.value.size - 1 && !_isLoading.value) {
            loadMoreItemsIfNeeded()
        }
    }

    private fun loadMoreItemsIfNeeded() {
        viewModelScope.launch {
            if (_isLoading.value) return@launch
            _isLoading.value = true

            delay(1000)

            val nextPageItems = allItems
                .drop(currentPage * pageSize)
                .take(pageSize)

            _items.value += nextPageItems
            currentPage++
            _isLoading.value = false
        }
    }
}



