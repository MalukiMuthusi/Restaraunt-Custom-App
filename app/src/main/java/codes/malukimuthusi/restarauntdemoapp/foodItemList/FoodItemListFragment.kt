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
        // set View Model
        val application = requireNotNull(this.activity).application
        val dataSource = FoodItemDatabase.getInstance(application).foodItemDatabaseDao
        val viewModelFactory = FoodItemListViewModelFactory(dataSource, application)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(FoodItemListViewModel::class.java)
        binding.viewModel = viewModel


        val adapter =
            FoodItemAdapter(
                FoodItemListener { foodId ->
//                    Toast.makeText(context, "${foodId}", Toast.LENGTH_LONG).show()

                    viewModel.foodItemClicked(foodId)
                })

        binding.foodItemList.adapter = adapter


        // Observe the List and notify Any changes
        viewModel.foodItemList?.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        // observe item clicked to navigate
        viewModel.navigateToDetails.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(
                    FoodItemListFragmentDirections.actionFoodItemListToFoodDetailFragment()
                )
                viewModel.navigatedToDetails()
            }
        })


        return binding.root
    }
}