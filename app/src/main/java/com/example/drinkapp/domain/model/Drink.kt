package com.example.drinkapp.domain.model


import com.google.gson.annotations.SerializedName

data class Drink(
    @SerializedName("strCategory")
    val strCategory: String
)