package codes.malukimuthusi.restarauntdemoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import codes.malukimuthusi.restarauntdemoapp.databinding.FragmentFoodItemListBinding

class FoodItemList : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFoodItemListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_food_item_list, container, false)

        val adapter =
            FoodItemAdapter(
                FoodItemListener { foodId ->
                    Toast.makeText(context, "${foodId}", Toast.LENGTH_LONG).show()
                })
        binding.foodItemList.adapter = adapter

        return binding.root
    }
}