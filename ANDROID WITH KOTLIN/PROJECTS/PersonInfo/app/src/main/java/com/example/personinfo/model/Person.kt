package com.example.personinfo.model

data class PersonResponse(
    val person: Person
)

data class Person(
    val id: Int,
    val name: String,
    val country: Country?,
    val birthday: String?,
    val gender: String?
)

data class Country(
    val name: String,
    val code: String
)