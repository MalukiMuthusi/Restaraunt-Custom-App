package codes.malukimuthusi.restarauntdemoapp.createFoodItem

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CreateFoodItemViewModelFactory(
    val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateFoodItemViewModel::class.java))
            return CreateFoodItemViewModel(application) as T
        throw IllegalArgumentException("Unknown ViewModel class")

    }

}