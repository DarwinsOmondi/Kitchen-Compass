package com.example.kitchencompass

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL: String = "https://www.themealdb.com/api/json/v1/1/"
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val Api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
