package com.example.drinkapp.domain.api_repository

import com.example.drinkapp.domain.model.CategoriesModel
import com.example.drinkapp.domain.retrofit.RetrofitInstance
import retrofit2.Response

class ApiRepository {

    suspend fun getCategories(): Response<CategoriesModel> {
        return RetrofitInstance.getCategories().getCategories()
    }

}