package com.example.drinkapp.presentation.vm

import androidx.lifecycle.*
import com.example.drinkapp.data.local_db.DrinkRepository
import com.example.drinkapp.data.model.FavoritesModel
import com.example.drinkapp.data.model.HistoryModel
import kotlinx.coroutines.launch

class HistoryVM(private val repository: DrinkRepository) : ViewModel() {

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
}

class HistoryViewModelFactory(private val repository: DrinkRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryVM::class.java)) {
            return HistoryVM(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}