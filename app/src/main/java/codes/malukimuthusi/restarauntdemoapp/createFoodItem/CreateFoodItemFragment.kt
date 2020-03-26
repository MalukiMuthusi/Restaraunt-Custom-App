package codes.malukimuthusi.restarauntdemoapp.createFoodItem

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import codes.malukimuthusi.restarauntdemoapp.R
import codes.malukimuthusi.restarauntdemoapp.databinding.CreateFoodItemFragmentBinding

class CreateFoodItemFragment : Fragment() {



    private lateinit var viewModel: CreateFoodItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: CreateFoodItemFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.create_food_item_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = CreateFoodItemViewModelFactory(application)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CreateFoodItemViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        // observe some navigation data to navigate.
        viewModel.toNavigate.observe(viewLifecycleOwner, Observer { nav ->
            nav?.let {
                this.findNavController().navigate(
                    CreateFoodItemFragmentDirections.actionCreateFoodItemFragmentToFoodItemList()
                )
                viewModel.endNavigateToList()
            }

        })



        return binding.root
    }

}
