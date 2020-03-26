package codes.malukimuthusi.restarauntdemoapp.foodDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import codes.malukimuthusi.restarauntdemoapp.R
import codes.malukimuthusi.restarauntdemoapp.database.FoodItemDatabase
import codes.malukimuthusi.restarauntdemoapp.databinding.FragmentFoodDetailBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FoodDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentFoodDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_food_detail, container, false)

        /*
        * extract the arguments passed on from the fragment.
        * */
        val arguments = arguments?.let { FoodDetailFragmentArgs.fromBundle(it) }
        val foodID = arguments?.foodID

        /*
        * create ViewModel Object.
        * */

        // get application context
        val application = requireNotNull(this.activity).application
        val dataSource = FoodItemDatabase.getInstance(application).foodItemDatabaseDao
        // now create object  of viewModelFactory
        val viewModelFactory = foodID?.let {
            FoodDetailViewModelFactory(
                dataSource, application,
                it
            )
        }
        // create viewModel Object using the factory method.
        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(FoodDetailViewModel::class.java)
        // bind viewmodel
        binding.viewModel = viewModel
        // set the lifeCyle owner
        binding.lifecycleOwner = this

        /*
        * the last statement in on create.
        * */
        return binding.root
    }


}
