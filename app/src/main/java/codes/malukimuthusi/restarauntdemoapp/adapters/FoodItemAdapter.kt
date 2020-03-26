package codes.malukimuthusi.restarauntdemoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import codes.malukimuthusi.restarauntdemoapp.database.FoodItem
import codes.malukimuthusi.restarauntdemoapp.databinding.FoodItemBinding
import codes.malukimuthusi.restarauntdemoapp.databinding.HeaderBinding

// holders the Header Label Class  Type.
private const val ITEM_VIEW_TYPE_HEADER = 0
// holders the Food Item class Type.
private const val ITEM_VIEW_TYPE_ITEM = 1

class FoodItemAdapter(private val clickListener: FoodItemListener) :
    ListAdapter<Data, RecyclerView.ViewHolder>(
        FoodItemDiffCallBack()
    ) {

    /*
    * When the view Holder is created.
    * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }

    }

    /*
    * */
    fun addHeaderAndSubmitList(list: List<FoodItem>) {
        val items = when (list) {
            null -> listOf(Data.Header)
            else -> listOf(Data.Header) + list.map { Data.FoodItemData(it) }
        }
        submitList(items)
    }

    /*
    * Get the Item Type of data at Position provided
    * */
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Data.FoodItemData -> ITEM_VIEW_TYPE_ITEM
            is Data.Header -> ITEM_VIEW_TYPE_HEADER
        }
    }

    /*
    * Only bind Food Item when the ViewHolder is of Data List.
    *  */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        /*
        * check the type of ViewHolder.
        *   If it is the ViewHolder that has FoodItem.
        *   Bind data.*/
        when (holder) {
            is ViewHolder -> {
                val foodItem = getItem(position) as Data.FoodItemData
                holder.bind(foodItem.foodItem, clickListener)
            }
        }

    }

    /*
    * ViewHolder for a single food Item
    * */
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

    /*
    * ViewHolder for the Header.
    * */
    class HeaderViewHolder private constructor(private val binding: HeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {


        companion object {
            fun from(parent: ViewGroup): HeaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HeaderBinding.inflate(layoutInflater, parent, false)
                return HeaderViewHolder(binding)
            }
        }
    }

}

/*
* Function to calculate data changes in the List.
*   It tells if the Item to be displayed is new one or it is in the cache.
* */
class FoodItemDiffCallBack : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }

}

/*
* This is the click Listener for listening to clicks on the Items in the List.
*   This class implements only a single function.
*   The function takes a name lambda expression as a parameter.
* */
class FoodItemListener(val clickListener: (fooId: Long) -> Unit) {
    fun onClick(foodItem: FoodItem) {
        return clickListener(foodItem.foodId)

    }
}

/*
* A class that holds all types of data displayed in the List.
* The classes include: FoodItem and Header Class.
* Food Item is the real data.
* Header is label for the Items.
* */
sealed class Data {

    // id used to calculate the changes DiffUtil.ItemCallback
    abstract val id: Long

    // encapsulates the FoodItem Data class.
    data class FoodItemData(val foodItem: FoodItem) : Data() {
        override val id: Long
            get() = foodItem.foodId
    }

    // only one instance of the Header is used.
    object Header : Data() {
        override val id: Long
            // Long.MIN_VALUE is a very small integer number
            get() = Long.MIN_VALUE
    }

}



