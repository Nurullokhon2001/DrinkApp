package com.example.drinkapp.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drinkapp.data.network_repository.ApiRepository
import com.example.drinkapp.domain.model.DrinkDetails
import com.example.drinkapp.domain.model.Drinks
import kotlinx.coroutines.launch
import retrofit2.Response

class CategoriesVm : ViewModel() {

    private val repo = ApiRepository()
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var isLoading: LiveData<Boolean> = _isLoading

    private fun setLoading(isLoading: Boolean) {
        _isLoading.postValue(isLoading)
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