package com.example.kitchencompass

import retrofit2.http.GET

interface ApiService {
    @GET("categories.php")
    suspend fun getFoods(): Foods
}
