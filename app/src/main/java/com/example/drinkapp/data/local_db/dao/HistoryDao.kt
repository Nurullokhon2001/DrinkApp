package com.example.drinkapp.data.local_db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.drinkapp.data.model.FavoritesModel
import com.example.drinkapp.data.model.HistoryModel
import kotlinx.coroutines.flow.Flow


@Dao
interface HistoryDao {

    //History
    @Query("Select * from History")
    fun getDrinkHistory(): Flow<List<HistoryModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHistory(history: HistoryModel)

    @Query("Delete from History")
    suspend fun deleteAllHistory()

    @Query("Delete from History Where idDrink = :id")
    suspend fun deleteHistory(id: Int)

    //Favorites
    @Query("Select * from Favorite")
    fun getFavorites(): Flow<List<FavoritesModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteDrink(favoritesModel: FavoritesModel)

    @Query("Delete from Favorite where idDrink = :id")
    fun deleteFavorites(id: Int)

//    @Query("SELECT EXISTS (SELECT 1 FROM example_table WHERE id = :id)")
//    fun exists(id: Int): Boolean

}