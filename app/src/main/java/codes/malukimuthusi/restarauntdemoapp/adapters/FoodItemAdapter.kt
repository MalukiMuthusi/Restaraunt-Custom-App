package codes.malukimuthusi.restarauntdemoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import codes.malukimuthusi.restarauntdemoapp.database.FoodItem
import codes.malukimuthusi.restarauntdemoapp.databinding.FoodItemBinding

class FoodItemAdapter(private val clickListener: FoodItemListener) :
    ListAdapter<FoodItem, FoodItemAdapter.ViewHolder>(
        FoodItemDiffCallBack()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = getItem(position)

        holder.bind(getItem(position)!!, clickListener)
    }

    class ViewHolder private constructor(private val binding: FoodItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        /*
        * bind  data here.
        * binding.executePendingBindings()
        *   binds data defined elsewhere.
        * */
        fun bind(item: FoodItem, clickListener: FoodItemListener) {
            binding.foodItemData = item
            binding.foodItemData4Listener = clickListener
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FoodItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(
                    binding
                )
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

class FoodItemListener(val clickListener: (fooId: Long) -> Unit) {
    fun onClick(foodItem: FoodItem) {
        return clickListener(foodItem.foodId)

    }
}



