package codes.malukimuthusi.restarauntdemoapp.foodItemList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import codes.malukimuthusi.restarauntdemoapp.R
import codes.malukimuthusi.restarauntdemoapp.adapters.FoodItemAdapter
import codes.malukimuthusi.restarauntdemoapp.adapters.FoodItemListener
import codes.malukimuthusi.restarauntdemoapp.database.FoodItemDatabase
import codes.malukimuthusi.restarauntdemoapp.databinding.FragmentFoodItemListBinding

class FoodItemListFragment : Fragment() {

    // viewModel
    private lateinit var viewModel: FoodItemListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFoodItemListBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_food_item_list, container, false
            )
        /*
        * set the lifecyle owner of this data binding.
        * */
//        binding.lifecycleOwner = this

        /*
        * Create ViewModel
        *   ViewModel Holds data and logic for this fragment
        * */
        val application = requireNotNull(this.activity).application
        val dataSource = FoodItemDatabase.getInstance(application).foodItemDatabaseDao
        val viewModelFactory = FoodItemListViewModelFactory(dataSource, application)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(FoodItemListViewModel::class.java)
        binding.viewModel = viewModel

        /*
        * Create a Recycle View Adapter.
        *   The adapter, adapts between viewHolder and RecyclerView List.
        * */
        val adapter =
            FoodItemAdapter(
                FoodItemListener { foodId ->
                    foodId.let {
                        viewModel.onClickNavigateToDetails()
//                        Toast.makeText(context, "${foodId}", Toast.LENGTH_SHORT).show()
                    }

                })

        binding.foodItemList.adapter = adapter

        /*
        * Submit data to the Adapter.
        *   Observe for changes.
        * */
        viewModel.foodItemList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        /*
        * Observe Food Id to navigate to its Details Fragment.
        * */
        viewModel.navigateToDetailsFragment.observe(viewLifecycleOwner, Observer { foodID ->
            foodID.let {
                this.findNavController().navigate(
                    FoodItemListFragmentDirections.actionFoodItemListToFoodDetailFragment(foodID)
                )
                viewModel.stopNavigation()
            }
        })


        return binding.root
    }
}