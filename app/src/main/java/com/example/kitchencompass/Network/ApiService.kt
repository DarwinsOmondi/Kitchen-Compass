package com.example.kitchencompass.Network

import com.example.kitchencompass.Model.Foods
import com.example.kitchencompass.Model.RandomFood
import retrofit2.http.GET

interface ApiService {
    @GET("categories.php")
    suspend fun getFoods(): Foods

    @GET("random.php")
    suspend fun randomFood(): RandomFood
}