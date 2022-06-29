package com.example.practice.data.repository.Remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface UserApiInterface {



    @GET("volley_array.json")
    suspend fun getUsers() : String
}