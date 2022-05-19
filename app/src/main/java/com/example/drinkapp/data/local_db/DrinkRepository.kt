package com.example.drinkapp.data.local_db

import androidx.annotation.WorkerThread
import com.example.drinkapp.data.model.HistoryModel
import com.example.drinkapp.data.local_db.dao.HistoryDao
import com.example.drinkapp.data.model.FavoritesModel
import kotlinx.coroutines.flow.Flow

class DrinkRepository(private val historyDao: HistoryDao) {

    val getDrinkHistory: Flow<List<HistoryModel>> = historyDao.getDrinkHistory()

    val getDrinkFavorites: Flow<List<FavoritesModel>> = historyDao.getFavorites()

    suspend fun insertHistory(historyModel: HistoryModel) {
        historyDao.insertHistory(historyModel)
    }

    suspend fun insertFavorite(historyModel: FavoritesModel) {
        historyDao.insertFavoriteDrink(historyModel)
    }

//    suspend fun

}