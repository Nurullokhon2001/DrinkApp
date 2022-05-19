package com.example.drinkapp.data.local_db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.drinkapp.data.model.HistoryModel
import kotlinx.coroutines.flow.Flow


@Dao
interface HistoryDao {

    @Query("Select * from History")
    fun getDrinkHistory(): Flow<List<HistoryModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHistory(history: HistoryModel)

    @Query("Delete from History")
    suspend fun deleteAllHistory()

    @Query("Delete from History Where idDrink = :id")
    suspend fun deleteHistory(id: Int)

}