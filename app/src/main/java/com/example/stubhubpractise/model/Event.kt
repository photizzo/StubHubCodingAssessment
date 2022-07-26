package com.example.stubhubpractise.model

data class Event(
    val id: Int,
    val name: String,
    val city: String,
    val price: Int,
    val date: String,
    val events: List<Any> = listOf(),
)
