package com.example.kitchencompass

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FoodViewModel : ViewModel() {
    private val _foodData = MutableLiveData<List<Category>>()
    val foodData: LiveData<List<Category>> = _foodData

    init {
        fetchFoodDetails()
    }
    private fun fetchFoodDetails() {
        viewModelScope.launch {
            try {
                val foodResponse = RetrofitClient.Api.getFoods()
                _foodData.value = foodResponse.categories
            } catch (e: Exception) {
                // Handle error
                e.message
            }
        }
    }
}
