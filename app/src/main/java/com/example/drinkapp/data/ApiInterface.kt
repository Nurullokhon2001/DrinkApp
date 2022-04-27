package com.example.drinkapp.data

import com.example.drinkapp.domain.model.CategoriesModel
import com.example.drinkapp.domain.model.Drinks
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    companion object {
        //http://www.thecocktaildb.com/api/json/v1/1/list.php?c=list
        const val baseUrl = "http://www.thecocktaildb.com/api/json/v1/1/"
    }

    @GET("list.php?c=list")
    suspend fun getCategories(): Response<CategoriesModel>

    //http://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Cocktail
    @GET("filter.php?")
    suspend fun getDrinksByCategories(
        @Query("c") c: String
    ): Response<Drinks>

}