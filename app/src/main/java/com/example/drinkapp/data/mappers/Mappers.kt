package com.example.drinkapp.data.mappers

import com.example.drinkapp.data.model.FavoritesModel
import com.example.drinkapp.data.model.HistoryModel
import com.example.drinkapp.domain.model.Drink

class Mappers {

    companion object {
        fun mapDrinkToHistoryModel(drink: Drink): HistoryModel {
            return HistoryModel(
                drink.idDrink,
                drink.strDrink,
                drink.strDrinkThumb
            )
        }

        fun mapDrinkToFavoritesModel(drink: Drink): FavoritesModel {
            return FavoritesModel(
                drink.idDrink,
                drink.strDrink,
                drink.strDrinkThumb
            )
        }
    }

}