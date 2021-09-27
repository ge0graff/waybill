package com.example.waybill.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.waybill.R
import com.example.waybill.cars.CarsRecyclerAdapter
import com.example.waybill.databinding.FragmentCarInfoBinding


class CarInfoFragment : Fragment(R.layout.fragment_cars) {
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

        binding.carInfoCarNameText.text = CarsRecyclerAdapter.carName
        binding.carInfoCarMileageValue.text = CarsRecyclerAdapter.mileage
        binding.carInfoConsumptionSummerValue.text = CarsRecyclerAdapter.consumption_summer
        binding.carInfoConsumptionWinterValue.text = CarsRecyclerAdapter.consumption_winter
        binding.carInfoFuelValue.text = CarsRecyclerAdapter.fuel_value

//        Тут пытался получить бандл
//        val bundle = this.arguments
//        if (bundle != null) {
//
//            binding.carInfoCarNameText.text = bundle.getString("MyArg")
//        } else {
//            binding.carInfoCarNameText.text = "Неудача"
//        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = CarInfoFragment()
            }

}
