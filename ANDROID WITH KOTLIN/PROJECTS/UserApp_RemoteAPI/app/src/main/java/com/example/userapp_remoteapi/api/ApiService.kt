package com.example.userapp_remoteapi.api

import com.example.userapp_remoteapi.model.User
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getUsers(): Call<List<User>>
    /*@GET("todos")
    fun getUsers(): Call<List<Todo>>*/
}
