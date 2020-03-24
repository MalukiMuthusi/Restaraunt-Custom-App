package codes.malukimuthusi.restarauntdemoapp.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import codes.malukimuthusi.restarauntdemoapp.R
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

@BindingAdapter("setFoodImage")
fun ImageView.setFoodImage(item: FoodItem?) {
    setImageResource(R.drawable.food)
}