package com.example.lwb.foodBase.presentation.foodList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lwb.core.data.dao.ProductDao
import com.example.lwb.core.data.entities.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodListViewModel @Inject constructor(
    private val productDao: ProductDao
) : ViewModel() {

    private val _allProducts = MutableStateFlow<List<Product>>(emptyList())
    val allProducts: StateFlow<List<Product>> = _allProducts
    private val _state = MutableStateFlow(FoodListState())
    val state: StateFlow<FoodListState> = _state

    private val _searchResults = MutableStateFlow<List<Product>>(emptyList())
    val searchResults: StateFlow<List<Product>> = _searchResults

    fun onEvent(event: FoodListEvent) {
        viewModelScope.launch {
            handleEvent(event)
        }
    }

    private suspend fun handleEvent(event: FoodListEvent) {
        when (event) {
            is FoodListEvent.OnSearchQueryChange -> {
                _state.value = _state.value.copy(
                    searchQuery = event.query
                )
                if (event.query.isNotEmpty()) {
                    searchProducts(event.query)
                }
            }
        }
    }

    private suspend fun searchProducts(query: String) {
        _searchResults.value = productDao.getByNameU(query)
    }

    fun loadProducts() {
        viewModelScope.launch {
            _allProducts.value = productDao.getAllProducts()
        }
    }
}