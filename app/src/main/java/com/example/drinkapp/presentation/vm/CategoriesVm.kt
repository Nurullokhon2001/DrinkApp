package com.example.drinkapp.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drinkapp.domain.model.CategoriesModel
import com.example.drinkapp.domain.api_repository.ApiRepository
import com.example.drinkapp.domain.model.DrinkDetails
import com.example.drinkapp.domain.model.Drinks
import com.example.drinkapp.domain.model.IngredientModel
import com.example.drinkapp.domain.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Response

class CategoriesVm : ViewModel() {

    private val repo = ApiRepository()


    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var isLoading: LiveData<Boolean> = _isLoading

    private fun setLoading(isLoading: Boolean) {
        _isLoading.postValue(isLoading)
    }


    private val _ctgr = MutableLiveData<Response<CategoriesModel>>()
    val ctgr = _ctgr
    fun getCtgr() {
        viewModelScope.launch {
            setLoading(true)
            _ctgr.value = repo.getCategories()
        }
    }

    private val _drinks = MutableLiveData<Response<Drinks>>()
    val drinks = _drinks
    fun getDrinks(ctgrName: String) {
        viewModelScope.launch {
            setLoading(true)
            _drinks.value = repo.getDrinksByCategories(ctgrName)
            setLoading(false)
        }
    }


    fun getDetailsDrinkById(id: String): LiveData<Response<DrinkDetails>> {
        val detail = MutableLiveData<Response<DrinkDetails>>()
        viewModelScope.launch {
            setLoading(true)
            detail.value = repo.getDetailsDrinkById(id)
            setLoading(false)
        }
        return detail
    }

    fun getIngredientById(id: String): LiveData<Response<IngredientModel>> {
        val ingredient = MutableLiveData<Response<IngredientModel>>()
        viewModelScope.launch {
            setLoading(true)
            ingredient.value = repo.getIngredientById(id)
            setLoading(false)
        }
        return ingredient
    }

    fun getDrinksByName(name: String): LiveData<Response<DrinkDetails>> {
        val detail = MutableLiveData<Response<DrinkDetails>>()
        viewModelScope.launch {
            setLoading(true)
            detail.value = repo.getDrinksByName(name)
            setLoading(false)
        }
        return detail
    }

}