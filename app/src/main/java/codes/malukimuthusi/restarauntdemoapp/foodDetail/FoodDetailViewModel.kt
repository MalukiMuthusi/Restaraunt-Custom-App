package codes.malukimuthusi.restarauntdemoapp.foodDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import codes.malukimuthusi.restarauntdemoapp.database.FoodItem
import codes.malukimuthusi.restarauntdemoapp.database.FoodItemDatabaseDao
import kotlinx.coroutines.*


class FoodDetailViewModel(
    private val dataSource: FoodItemDatabaseDao,
    application: Application,
    private val foodID: Long = 1L
) :
    AndroidViewModel(application) {

    // get the Food ID that was passed on.
    private lateinit var foodItem: LiveData<FoodItem>

    init {

        getFoodItem()
    }

    /*
    * Coroutines
    * */
    private val viewModelJob = Job()

    // scope of the job.
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    /*
    * Get an Item From the database.
    * call function A on running thread. Function a LAUNCHES a coroutine. Then calls a suspend function.
    * The suspend function do process on different thread.
    * */
    private fun getFoodItem() {
        uiScope.launch {
            getItemFromDb()
        }
    }

    private suspend fun getItemFromDb() {
        withContext(Dispatchers.IO) {
            foodItem = dataSource.getFoodItemWithID(foodID)
        }
    }

    /*
    * data used in the UI
    * */
    val title = foodItem.value?.foodTitle
    val description = foodItem.value?.foodDescription


    override fun onCleared() {
        super.onCleared()

        // cancel all jobs started by this viewmodel.
        viewModelJob.cancel()
    }
}