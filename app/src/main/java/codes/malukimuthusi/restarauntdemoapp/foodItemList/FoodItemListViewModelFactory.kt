package codes.malukimuthusi.restarauntdemoapp.foodItemList

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import codes.malukimuthusi.restarauntdemoapp.database.FoodItemDatabaseDao
import javax.sql.CommonDataSource

class FoodItemListViewModelFactory(
    private val dataSource: FoodItemDatabaseDao,
    private val application: Application

) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodItemListViewModel::class.java)) {
            return FoodItemListViewModel(dataSource, application) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}