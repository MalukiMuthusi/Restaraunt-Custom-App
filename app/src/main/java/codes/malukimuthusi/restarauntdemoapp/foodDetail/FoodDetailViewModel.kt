package codes.malukimuthusi.restarauntdemoapp.foodDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import codes.malukimuthusi.restarauntdemoapp.database.FoodItemDatabaseDao

class FoodDetailViewModel(val dataSource: FoodItemDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    // get the Food ID that was passed on.

}