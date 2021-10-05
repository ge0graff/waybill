package com.example.waybill.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.waybill.cars.CarsRecyclerAdapter
import com.example.waybill.data.carselect.SelectedCar
import com.example.waybill.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mfCarName.text = SelectedCar.name
        binding.mfCalculate.setOnClickListener {
            calculate()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    @SuppressLint("SetTextI18n")
    fun calculate (){
        var dMileage: Float
        var fCL: Float
        val Mileage = binding.mfMileageValue.text.toString()
        val ML = SelectedCar.mileage
        val fVL = SelectedCar.fuel_value.toFloat()
        val con = SelectedCar.consumption_summer.toFloat()
        dMileage = Mileage.toFloat() - ML.toFloat()
        fCL = (fVL - (dMileage / 100 * con))
        binding.mfCarDailyMileageValue.text = dMileage.toInt().toString() + " км"
        binding.mfFuelValue.text = fCL.toInt().toString() + " л"


    }
}