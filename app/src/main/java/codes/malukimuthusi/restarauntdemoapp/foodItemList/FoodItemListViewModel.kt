package codes.malukimuthusi.restarauntdemoapp.foodItemList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import codes.malukimuthusi.restarauntdemoapp.database.FoodItemDatabaseDao
import kotlinx.coroutines.*


class FoodItemListViewModel(val dataSource: FoodItemDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    // create a job object
    private var viewModelJob = Job()


    /*
    * get list of food Items from Database
    * */
     val foodItemList = dataSource.listOfFoodItems()


    /*
    * Navigate to Food Item Fragment
    * */
    private val _navigateToDetailsFragment = MutableLiveData<Long>()
    val navigateToDetailsFragment : LiveData<Long>
        get() = _navigateToDetailsFragment

    fun onClickNavigateToDetails(id: Long = 1L) {
        _navigateToDetailsFragment.value = id
    }

    /*
    * finished navigating
    * */
    fun stopNavigation() {
        _navigateToDetailsFragment.value = null
    }

    override fun onCleared() {
        super.onCleared()

        // cancel all jobs started on this viewModel
        viewModelJob.cancel()
    }
}
