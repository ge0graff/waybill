//package com.example.waybill.presentation.ui.fragments.carinfofragment
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import com.example.waybill.databinding.FragmentCarInfoBinding
//
//
//class CarInfoFragment : Fragment() {
//
//    companion object{
//        fun getNewInstance(arg: Bundle?): CarInfoFragment {
//            val carInfoFragment = CarInfoFragment()
//            carInfoFragment.arguments = arg
//            return carInfoFragment
//        }
//    }
//
//    private lateinit var binding: FragmentCarInfoBinding
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentCarInfoBinding.inflate(inflater)
//        return binding.root
//    }
//
//    override fun onStart() {
//        super.onStart()
//        putText()
//    }
//
//    private fun putText(){
//        binding.carInfoCarNameText.text = arguments?.getString("name")
//        binding.carInfoCarMileageValue.text = arguments?.getString("mileage")
//        binding.carInfoConsumptionSummerValue.text = arguments?.getString("cSum")
//        binding.carInfoConsumptionWinterValue.text = arguments?.getString("cWin")
//        binding.carInfoFuelValue.text = arguments?.getString("fuel")
//    }
//
//}

