package com.example.drinkapp.repository

import android.util.Log
import com.example.drinkapp.model.CategoriesModel
import com.example.drinkapp.retrofit.RetrofitInstance
import retrofit2.Response

class ApiRepository {

    suspend fun getCategories(): Response<CategoriesModel> {
        return RetrofitInstance.getCategories().getCategories()
    }

}