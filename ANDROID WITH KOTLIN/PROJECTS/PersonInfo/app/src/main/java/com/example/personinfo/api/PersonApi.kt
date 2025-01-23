package com.example.personinfo.api

import com.example.personinfo.model.Person
import com.example.personinfo.model.PersonResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonApi {

    @GET("people")
    fun getAllPeople(@Query("q") q: String): Call<List<PersonResponse>>

    @GET("people")
    fun getPersonById(@Query("id") id: Int): Call<Person>

    companion object {
        fun create(): PersonApi {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.tvmaze.com/search/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(PersonApi::class.java)
        }
    }

}
