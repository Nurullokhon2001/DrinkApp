package com.example.drinkapp

import android.app.Application
import com.example.drinkapp.data.local_db_repository.DrinkRepository
import com.example.drinkapp.domain.local_db.DrinkRoomDataBase

class App : Application() {
    private val dataBase by lazy { DrinkRoomDataBase.getDataBase(this) }
    val repository by lazy { DrinkRepository(dataBase.historyDao()) }
}