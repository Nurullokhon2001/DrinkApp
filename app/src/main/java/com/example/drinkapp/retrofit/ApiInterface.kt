package com.example.drinkapp.retrofit

import com.example.drinkapp.model.CategoriesModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    companion object{
        //http://www.thecocktaildb.com/api/json/v1/1/list.php?c=list
        const val baseUrl = "http://www.thecocktaildb.com/api/json/v1/1/"
    }

    @GET("list.php?c=list")
  suspend  fun getCategories(): Response<CategoriesModel>

}