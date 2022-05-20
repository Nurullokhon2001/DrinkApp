package com.example.drinkapp.presentation.vm

import androidx.lifecycle.*
import com.example.drinkapp.data.local_db.DrinkRepository
import com.example.drinkapp.data.model.CategoriesDBModel
import com.example.drinkapp.data.model.FavoritesModel
import com.example.drinkapp.data.model.HistoryModel
import kotlinx.coroutines.launch

class RoomViewModel(private val repository: DrinkRepository) : ViewModel() {

    val allHistory: LiveData<List<HistoryModel>> = repository.getDrinkHistory.asLiveData()

    val allFavorites: LiveData<List<FavoritesModel>> = repository.getDrinkFavorites.asLiveData()

    fun insertHistory(historyModel: HistoryModel) {
        viewModelScope.launch {
            repository.insertHistory(historyModel)
        }
    }

    fun insertFavorites(favorites: FavoritesModel) {
        viewModelScope.launch {
            repository.insertFavorite(favorites)
        }
    }

    fun deleteHistory(id: Int) {
        viewModelScope.launch { repository.deleteHistory(id) }
    }

    fun deleteFavorites(id: Int) {
        viewModelScope.launch { repository.deleteFavorite(id) }
    }

    fun insertCategories(categories: List<CategoriesDBModel>){
        viewModelScope.launch {
            repository.insertCategories(categories)
        }
    }

}

class RoomViewModelFactory(private val repository: DrinkRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomViewModel::class.java)) {
            return RoomViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}