package codes.malukimuthusi.restarauntdemoapp.createFoodItem

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateFoodItemViewModel(
    application: Application
) : AndroidViewModel(application) {

    // navigate
    private var _toNavigate = MutableLiveData<Boolean>()
    val toNavigate : LiveData<Boolean>
        get() = _toNavigate


//    This function helps to navigate
    fun navigateToList(){
        _toNavigate.value = true
}

    // end of navigation
    fun endNavigateToList() {
        _toNavigate.value = null
    }
}
