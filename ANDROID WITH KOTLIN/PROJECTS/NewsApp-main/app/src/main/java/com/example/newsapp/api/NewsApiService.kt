package com.example.newsapp.api

import com.example.newsapp.model.SourcesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("sources")
    suspend fun getSources(@Query("apiKey")apiKey : String): Response<SourcesResponse>
}