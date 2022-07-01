package com.example.practice.data.repository.API

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory {

    companion object{
        const val BASE_URL = "https://reqres.in/api/"
        const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
    }

    private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply{
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    val builder = OkHttpClient.Builder()
        .addNetworkInterceptor(interceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)

    val gson = GsonBuilder()
        .setLenient()
        .setDateFormat(RetrofitFactory.DATE_FORMAT)
        .create()

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(RetrofitFactory.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(builder.build())
        .build()

    val userApiInterface: UserApiInterface = retrofit().create(
        UserApiInterface::class.java
    )

}