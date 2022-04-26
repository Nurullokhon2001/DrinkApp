package com.example.drinkapp.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drinkapp.model.CategoriesModel
import com.example.drinkapp.repository.ApiRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class CategoriesVm : ViewModel() {

    val repo = ApiRepository()

    private var ctgr = MutableLiveData<Response<CategoriesModel>>()

    fun getCtgr(): LiveData<Response<CategoriesModel>> {
        viewModelScope.launch {
            ctgr.value = repo.getCategories()
        }
        return ctgr
    }


}