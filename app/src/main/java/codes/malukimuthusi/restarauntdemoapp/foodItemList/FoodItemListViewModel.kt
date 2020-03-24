package codes.malukimuthusi.restarauntdemoapp.foodItemList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import codes.malukimuthusi.restarauntdemoapp.database.FoodItem
import codes.malukimuthusi.restarauntdemoapp.database.FoodItemDatabase
import codes.malukimuthusi.restarauntdemoapp.database.FoodItemDatabaseDao
import javax.sql.CommonDataSource


class FoodItemListViewModel(val dataSource: FoodItemDatabaseDao, application: Application) :
    AndroidViewModel(application) {

}
