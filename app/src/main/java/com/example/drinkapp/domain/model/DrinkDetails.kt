package com.example.drinkapp.domain.model

import java.io.Serializable

data class DrinkDetails(
    val drinks: List<DrinkDetailsModel>
) : Serializable