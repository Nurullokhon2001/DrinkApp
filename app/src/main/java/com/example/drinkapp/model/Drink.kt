package com.example.drinkapp.model


import com.google.gson.annotations.SerializedName

data class Drink(
    @SerializedName("strCategory")
    val strCategory: String
)