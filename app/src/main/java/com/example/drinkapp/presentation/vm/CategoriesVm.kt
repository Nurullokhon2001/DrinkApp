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


    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var isLoading: LiveData<Boolean> = _isLoading

    fun setLoading(isLoading: Boolean) {
        _isLoading.postValue(isLoading)
    }

    fun getCtgr(): LiveData<Response<CategoriesModel>> {
        val ctgr = MutableLiveData<Response<CategoriesModel>>()
        viewModelScope.launch {
            setLoading(true)
            ctgr.value = repo.getCategories()
        }
        return ctgr
    }

    fun getDrinks(ctgrName: String): LiveData<Response<Drinks>> {

        val drinks = MutableLiveData<Response<Drinks>>()
        viewModelScope.launch {
            setLoading(true)
            drinks.value = repo.getDrinksByCategories(ctgrName)
            setLoading(false)
        }

        return drinks
    }

}