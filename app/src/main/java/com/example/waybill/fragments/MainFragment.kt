package com.example.waybill.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.waybill.MainActivity
import com.example.waybill.data.model.Car
import com.example.waybill.data.CarsDatabase
import com.example.waybill.data.carselect.SelectedCar
import com.example.waybill.data.manager.DatabaseManagerHolder
import com.example.waybill.databinding.FragmentMainBinding



class MainFragment() : Fragment(){
    private lateinit var binding: FragmentMainBinding

    private val databaseManager = DatabaseManagerHolder.databaseManager
    private var carList: List<Car> = databaseManager?.getCars() ?: listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

    fun calculate () {
        val dailyMileage = binding.mfMileageValue.text.toString()
        val mileageSum: Float
        val fuelBalance: Float
        val mileage = SelectedCar.mileage
        val fuelValue = SelectedCar.fuel_value.toFloat()
        val con = SelectedCar.consumption_summer.toFloat()

        if (dailyMileage != ""){
            mileageSum = dailyMileage.toFloat() - mileage.toFloat()
            fuelBalance = (fuelValue - (con / 100 * mileageSum))
            binding.mfCarDailyMileageValue.text = mileageSum.toInt().toString() + " км"
            binding.mfFuelValue.text = fuelBalance.toInt().toString() + " л"

            databaseManager.getCar(SelectedCar.id)?.let {
                databaseManager.updateCar(
                    it.apply {
                        this.mileage = mileageSum.toString()
                        this.fuel_value = fuelBalance.toString()
                    }
                )
            }
        }else{
            Toast.makeText(context, "Введите значение", Toast.LENGTH_LONG).show()
        }





    }
}