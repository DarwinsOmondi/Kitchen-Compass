package com.example.kitchencompass.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kitchencompass.Model.Category
import com.example.kitchencompass.Model.Meal
import com.example.kitchencompass.Network.RetrofitClient
import kotlinx.coroutines.launch

class FoodViewModel : ViewModel() {
    private val _foodData = MutableLiveData<List<Category>>()
    val foodData: LiveData<List<Category>> = _foodData


    private val _randomFoodData = MutableLiveData<List<Meal>>()
    val randomFoodData:LiveData<List<Meal>> = _randomFoodData

    init {
        fetchFoodDetails()
        fetchRandomFoodDetails()
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
    private fun fetchRandomFoodDetails(){
        viewModelScope.launch {
            try {
                val randomFoodResponse = RetrofitClient.Random_API.randomFood()
                _randomFoodData.value = randomFoodResponse.meals
            }catch (e:Exception){
                e.message
            }
        }
    }
}