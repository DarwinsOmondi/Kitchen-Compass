package com.example.kitchencompass.Network

import com.example.kitchencompass.Model.Foods
import com.example.kitchencompass.Model.RandomFood
import com.example.kitchencompass.Model.SearchFood
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("categories.php")
    suspend fun getFoods(): Foods

    @GET("random.php")
    suspend fun randomFood(): RandomFood

    @GET("search.php")
    suspend fun getFood(@Query("a") foodName:String):SearchFood
}