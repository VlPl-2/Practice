package com.example.practice.data.repository.API

import com.example.practice.data.repository.API.GetAllData
import retrofit2.Response
import retrofit2.http.GET


interface UserApiInterface {
    @GET("users?page=2")
    suspend fun getUsers() : Response<GetAllData>
}