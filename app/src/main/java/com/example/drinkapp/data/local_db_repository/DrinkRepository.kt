package com.example.drinkapp.data.local_db_repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.drinkapp.data.model.HistoryModel
import com.example.drinkapp.domain.local_db.dao.HistoryDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class DrinkRepository(private val historyDao: HistoryDao) {

    val getDrinkHistory: Flow<List<HistoryModel>> = historyDao.getDrinkHistory()

    @WorkerThread
   suspend  fun insertHistory(historyModel: HistoryModel) {
        historyDao.insertHistory(historyModel)
    }

}