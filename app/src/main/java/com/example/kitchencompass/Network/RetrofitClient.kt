package com.example.kitchencompass.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL: String = "https://www.themealdb.com/api/json/v1/1/"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val Api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }


    private const val Random_BASE_URL:String = "https://www.themealdb.com/api/json/v1/1/"
    private val random_retrofit:Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Random_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val Random_API: ApiService by lazy {
        random_retrofit.create(ApiService::class.java)
    }
}