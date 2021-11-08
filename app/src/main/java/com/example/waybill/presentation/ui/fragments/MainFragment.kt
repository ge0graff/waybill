package com.example.waybill.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.waybill.data.objects.SelectedCar
import com.example.waybill.data.managers.DatabaseManagerHolder
import com.example.waybill.data.model.Waybills
import com.example.waybill.data.objects.DataObject
import com.example.waybill.databinding.FragmentMainBinding
import kotlin.math.roundToInt


class MainFragment() : Fragment(){

    private lateinit var binding: FragmentMainBinding
    private val databaseManager = DatabaseManagerHolder.databaseManager
//    private val waybillsDatabaseManager = WaybillsDatabaseManagerHolder.waybillsDatabaseManager
//    private var carList: List<Car> = carsDatabaseManager?.getCars() ?: listOf()

   private var mileageSum = 0.0f
   private var fuelRemaining = 0.0f
   private var currentMileage = ""
   private var refuelValue = ""




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
            save()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    fun calculate () {
        currentMileage = binding.mfMileageValue.text.toString()
        refuelValue = binding.mfRefuelValue.text.toString()
        val con = SelectedCar.consumption_summer

        if (currentMileage != ""){
            mileageSum = currentMileage.toFloat() - SelectedCar.mileage.toFloat()

            fuelRemaining = if(refuelValue != ""){
                (SelectedCar.fuel_value.toFloat() - (con.toFloat() / 100 * mileageSum))+refuelValue.toFloat()
            }else{
                (SelectedCar.fuel_value.toFloat() - (con.toFloat() / 100 * mileageSum))
            }

            binding.mfCarDailyMileageValue.text = mileageSum.toInt().toString() + " км"
            binding.mfFuelValue.text = fuelRemaining.roundToInt().toString() + " л"

            }
        else{
            Toast.makeText(context, "Введите значение", Toast.LENGTH_LONG).show()
            }
    }

        private fun save(){
            binding.mfSaveButton.setOnClickListener {
                SelectedCar.mileage = currentMileage
                SelectedCar.fuel_value = fuelRemaining.toString()
            databaseManager.getCar(SelectedCar.id)?.let {
                databaseManager.updateCar(
                    it.apply {
                        this.mileage = currentMileage
                        this.fuel_value = fuelRemaining.roundToInt().toString()
                    }
                )

            }
            val waybill = Waybills(null, SelectedCar.id, SelectedCar.mileage, mileageSum.toString(), fuelRemaining.toString(), refuelValue, DataObject.mouths)
                databaseManager.insertWaybills(waybill)

        }
    }
}