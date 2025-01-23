package com.example.news.apiService


import com.example.news.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("users")
    fun getAllUsers(): Call<List<User>>

    @GET("users")
    fun getUserById(@Query("id")id:Int):Call<User>
}