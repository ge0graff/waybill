package com.example.waybill.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.waybill.databinding.FragmentCarInfoBinding


class CarInfoFragment : Fragment() {

    companion object{
        fun getNewInstance(arg: Bundle?): CarInfoFragment{
            val carInfoFragment = CarInfoFragment()
            carInfoFragment.arguments = arg
            return carInfoFragment
        }
    }

    private lateinit var binding: FragmentCarInfoBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCarInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        putText()
    }



    private fun putText(){
        binding.carInfoCarNameText.text = arguments?.getString("name")
        binding.carInfoCarMileageValue.text = arguments?.getString("mileage")
        binding.carInfoConsumptionSummerValue.text = arguments?.getString("cSum")
        binding.carInfoConsumptionWinterValue.text = arguments?.getString("cWin")
        binding.carInfoFuelValue.text = arguments?.getString("fuel")
    }
}
