package com.example.drinkapp.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drinkapp.domain.model.CategoriesModel
import com.example.drinkapp.domain.api_repository.ApiRepository
import com.example.drinkapp.domain.model.Drinks
import kotlinx.coroutines.launch
import retrofit2.Response

class CategoriesVm : ViewModel() {

    val repo = ApiRepository()

    private var ctgr = MutableLiveData<Response<CategoriesModel>>()
    private var drinks = MutableLiveData<Response<Drinks>>()

    fun getCtgr(): LiveData<Response<CategoriesModel>> {
        viewModelScope.launch {
            ctgr.value = repo.getCategories()
        }
        return ctgr
    }

    fun getDrinks(ctgrName : String): LiveData<Response<Drinks>> {
        viewModelScope.launch {
            drinks.value = repo.getDrinksByCategories(ctgrName)
        }
        return drinks
    }

}