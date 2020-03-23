package codes.malukimuthusi.restarauntdemoapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import codes.malukimuthusi.restarauntdemoapp.R
import codes.malukimuthusi.restarauntdemoapp.database.FoodItem
import codes.malukimuthusi.restarauntdemoapp.databinding.FoodItemBinding

class FoodItemAdapter :
    ListAdapter<FoodItem, FoodItemAdapter.ViewHolder>(FoodItemDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: FoodItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FoodItem) {
            binding.foodItemData = item
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FoodItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}

class FoodItemDiffCallBack : DiffUtil.ItemCallback<FoodItem>() {
    override fun areItemsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean {
        return oldItem.foodId == newItem.foodId
    }

    override fun areContentsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean {
        return oldItem == newItem
    }

}


