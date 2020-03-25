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
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentFoodDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_food_detail, container, false)

        // create ViewModel Object.

        // get application context
        val application = requireNotNull(this.activity).application
        val dataSource = FoodItemDatabase.getInstance(application).foodItemDatabaseDao
        // now create object  of viewModelFactory
        val viewModelFactory = FoodDetailViewModelFactory(dataSource, application)
        // create viewModel Object using the factory method.
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(FoodDetailViewModel::class.java)
        // bind viewmodel
        binding.viewModel = viewModel
        // set the lifeCyle owner
        binding.lifecycleOwner = this

        /*
        * the last statement in on create.
        * */
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FoodDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FoodDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
