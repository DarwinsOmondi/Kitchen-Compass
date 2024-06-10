package com.example.kitchencompass.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kitchencompass.Model.MealX
import com.example.kitchencompass.Network.RetrofitClient
import kotlinx.coroutines.launch

class SearchViewModel(
    private val char:String
):ViewModel() {
    private val _searchFoodData = MutableLiveData<List<MealX>>()
    val searchFoodData: LiveData<List<MealX>> = _searchFoodData

    init {
        fetchSearchedFoodDetails()
    }
    private fun fetchSearchedFoodDetails(){
        viewModelScope.launch {
            try {
                val searchFoodResponse = RetrofitClient.searchFoodApi.getFood(char)
                _searchFoodData.value = searchFoodResponse.meals
            }catch (e:Exception){
                e.message
            }
        }
    }
}