package codes.malukimuthusi.restarauntdemoapp.foodItemList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import codes.malukimuthusi.restarauntdemoapp.database.FoodItem
import codes.malukimuthusi.restarauntdemoapp.database.FoodItemDatabaseDao
import kotlinx.coroutines.*


class FoodItemListViewModel(val dataSource: FoodItemDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    // create a job object
    private var viewModelJob = Job()

    // create a thread scope for threads
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // single food Item
    private var foodItem = MutableLiveData<FoodItem?>()
    private val food1 = FoodItem()
    private val food2 = FoodItem()
    private val food3 = FoodItem()
    private val food4 = FoodItem()
    private val food5 = FoodItem()
    private val food6 = FoodItem()
    private val food7 = FoodItem()

    init {
//        initializedFoodItem()

        // create items for the database.
//        insertFoodItems()

    }


    private fun insertFoodItems() {
        uiScope.launch {
            insertToDB()
        }
    }

    // get List of Food Items.
//    private var _foodItemList = getFoodItemsList()
    val foodItemList = dataSource.listOfFoodItems()
//        get() = _foodItemList as LiveData<List<FoodItem>>?


//    private fun getFoodItemsList() {
//        uiScope.launch {
//            _foodItemList = getListOfItemsFromDB() as Unit
//        }
//    }
//
//    private suspend fun getListOfItemsFromDB(): LiveData<List<FoodItem>>? {
//        return withContext(Dispatchers.IO) {
//            dataSource.listOfFoodItems()
//        }
//
//    }

    private suspend fun insertToDB() {
        withContext(Dispatchers.IO)
        {
            dataSource.insert(food1)
            dataSource.insert(food6)
            dataSource.insert(food4)
            dataSource.insert(food5)
            dataSource.insert(food3)
            dataSource.insert(food7)
            dataSource.insert(food2)
        }
    }

    private fun initializedFoodItem() {
        uiScope.launch {
            foodItem.value = getFoodItemFromDB()
        }
    }

    // this functions runs on separate thread.
    private suspend fun getFoodItemFromDB(): FoodItem? {
        return withContext(Dispatchers.IO)
        {
            dataSource.getFoodItem() as FoodItem?
        }

    }

    /*
    * Navigate to Food Item Fragment
    * */
    private val _navigateToDetails = MutableLiveData<Long>()
    val navigateToDetails
        get() = _navigateToDetails

    fun foodItemClicked(foodId: Long) {
        _navigateToDetails.value = foodId
    }

    /*
    * finished navigating
    * */
    fun navigatedToDetails() {
        _navigateToDetails.value = null
    }

    override fun onCleared() {
        super.onCleared()

        // cancel all jobs started on this viewModel
        viewModelJob.cancel()
    }
}
