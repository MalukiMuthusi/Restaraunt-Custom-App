package codes.malukimuthusi.restarauntdemoapp.foodDetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import codes.malukimuthusi.restarauntdemoapp.database.FoodItemDatabaseDao
import codes.malukimuthusi.restarauntdemoapp.foodItemList.FoodItemListViewModel

class FoodDetailViewModelFactory(
    private val dataSource: FoodItemDatabaseDao,
    private val application: Application,
    private val foodId: Long = 1L

) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodDetailViewModel::class.java)) {
            return FoodDetailViewModel(dataSource, application, foodId) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}