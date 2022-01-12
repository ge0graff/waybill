package com.example.waybill.presentation.ui.fragments.mainfragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waybill.data.dao.CarsDao
import com.example.waybill.data.dao.WaybillsDao
import com.example.waybill.data.model.Waybills
import com.example.waybill.data.objects.DataObject
import com.example.waybill.data.objects.SelectedCar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class MainFragmentViewModel @ViewModelInject constructor (
    private val carsDao: CarsDao,
    private val waybillsDao: WaybillsDao
): ViewModel() {

    val dalyMileageValueLive = MutableLiveData<String>()
    val fuelValueLive = MutableLiveData<String>()
    private var mileageSum = 0.0f
    private var fuelRemaining = 0.0f

        fun calculate(currentMileage: String, refuelValue: String) = viewModelScope.launch {
        mileageSum = currentMileage.toFloat() - SelectedCar.mileage.toFloat()
        fuelRemaining = if(refuelValue != ""){
            (SelectedCar.fuel_value.toFloat() - (SelectedCar.consumption_summer.toFloat() / 100 * mileageSum))+refuelValue.toFloat()
        }else{
            (SelectedCar.fuel_value.toFloat() - (SelectedCar.consumption_summer.toFloat() / 100 * mileageSum))
        }
        dalyMileageValueLive.value = mileageSum.toInt().toString()
        fuelValueLive.value = fuelRemaining.roundToInt().toString() + " л"
    }

    fun save(currentMileage: String, refuelValue: String) = viewModelScope.launch(Dispatchers.IO) {
        SelectedCar.mileage = currentMileage
        SelectedCar.fuel_value = fuelRemaining.toString()
        carsDao.getById(SelectedCar.id).let {
            carsDao.update(
                it.apply {
                    this.mileage = currentMileage
                    this.fuel_value = fuelRemaining.roundToInt().toString()
                }
            )
        }
        val waybill = Waybills(null, SelectedCar.id, SelectedCar.mileage, mileageSum.roundToInt().toString(),
            fuelRemaining.roundToInt().toString(), refuelValue, DataObject.mouths)
        waybillsDao.insert(waybill)
    }
}