package com.example.waybill.presentation.ui.fragments.mainfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waybill.data.managers.DatabaseManagerHolder
import com.example.waybill.data.model.Waybills
import com.example.waybill.data.objects.DataObject
import com.example.waybill.data.objects.SelectedCar
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class MainFragmentViewModel(
    private val databaseManagerHolder: DatabaseManagerHolder
): ViewModel() {

    val dalyMileageValueLive = MutableLiveData<String>()
    val fuelValueLive = MutableLiveData<String>()
    private var mileageSum = 0.0f
    private var fuelRemaining = 0.0f

        fun calculate(currentMileage: String, refuelValue: String){
        mileageSum = currentMileage.toFloat() - SelectedCar.mileage.toFloat()
        fuelRemaining = if(refuelValue != ""){
            (SelectedCar.fuel_value.toFloat() - (SelectedCar.consumption_summer.toFloat() / 100 * mileageSum))+refuelValue.toFloat()
        }else{
            (SelectedCar.fuel_value.toFloat() - (SelectedCar.consumption_summer.toFloat() / 100 * mileageSum))
        }
        dalyMileageValueLive.value = mileageSum.toInt().toString()
        fuelValueLive.value = fuelRemaining.roundToInt().toString() + " л"
    }

    fun save(currentMileage: String, refuelValue: String) = viewModelScope.launch{
        SelectedCar.mileage = currentMileage
        SelectedCar.fuel_value = fuelRemaining.toString()
        databaseManagerHolder.databaseManager.getCar(SelectedCar.id)?.let {
            databaseManagerHolder.databaseManager.updateCar(
                it.apply {
                    this.mileage = currentMileage
                    this.fuel_value = fuelRemaining.roundToInt().toString()
                }
            )
        }
        val waybill = Waybills(null, SelectedCar.id, SelectedCar.mileage, mileageSum.toString(),
            fuelRemaining.toString(), refuelValue, DataObject.mouths)
        databaseManagerHolder.databaseManager.insertWaybills(waybill)
    }
}