package codes.malukimuthusi.restarauntdemoapp.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import codes.malukimuthusi.restarauntdemoapp.database.FoodItem

@BindingAdapter("foodItemTitle")
fun TextView.setFoodTitle(item: FoodItem?) {
    item?.let {
        text = item.foodTitle
    }
}

@BindingAdapter("foodItemDescription")
fun TextView.setFoodItemDescription(item: FoodItem?) {
    item?.let {
        text = item.foodDescription
    }
}