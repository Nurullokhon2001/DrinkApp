package com.example.drinkapp.model


import com.google.gson.annotations.SerializedName

data class CategoriesModel(
    @SerializedName("drinks")
    val drinks: List<Drink>
)