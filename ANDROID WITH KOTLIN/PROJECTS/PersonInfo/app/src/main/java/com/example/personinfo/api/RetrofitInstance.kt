package com.example.personinfo.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//https://api.tvmaze.com/search/people?q=lauren
object RetrofitInstance {
    private const val BASE_URL="https://api.tvmaze.com/search/"

    val api:PersonApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PersonApi::class.java)
    }
}