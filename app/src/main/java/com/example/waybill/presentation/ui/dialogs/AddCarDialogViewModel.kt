package com.example.waybill.presentation.ui.dialogs

import android.app.Application
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.waybill.data.dao.CarsDao
import com.example.waybill.data.model.Car
import com.example.waybill.presentation.ui.mainActivity.ADD_CAR_RESULT_OK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddCarDialogViewModel @ViewModelInject constructor(
    application: Application,
    private val carsDao: CarsDao,
    @Assisted private val state: SavedStateHandle
): AndroidViewModel(application) {

    val car = state.get<Car>("car")
    val carNameLiveData = MutableLiveData<Boolean>()

    private fun createCar(car: Car) = viewModelScope.launch(Dispatchers.IO){
        carsDao.insert(car)
        addCarEditChannel.send(AddEditCarEvent.DismissDialog(ADD_CAR_RESULT_OK))
    }

    var carName = state.get<String>("carName") ?:car?.name ?: ""
        set(value) {
            field = value
            state.set("carName", value)
        }
    var carMileage = state.get<String>("carMileage") ?:car?.mileage ?: ""
        set(value) {
            field = value
            state.set("carMileage", value)
        }
    var consumptionSummer = state.get<String>("consumptionSummer") ?:car?.consumption_summer ?: ""
        set(value) {
            field = value
            state.set("consumptionSummer", value)
        }
    var consumptionWinter = state.get<String>("consumptionWinter") ?:car?.consumption_winter ?: ""
        set(value) {
            field = value
            state.set("consumptionWinter", value)
        }
    var fuelValue = state.get<String>("fuelValue") ?:car?.fuel_value ?: ""
        set(value) {
            field = value
            state.set("fuelValue", value)
        }


    private val addCarEditChannel = Channel<AddEditCarEvent>()
    val addEditCarEvent =addCarEditChannel.receiveAsFlow()

    fun onSaveClick() {
        when {
            carName.isBlank() -> {
                showInvalidInputCarNameMessage("Test")
                carNameLiveData.value = false
                return
            }
            carMileage.isBlank() -> {
                showInvalidInputCarMileageMessage("Введите пробег автомобиля")
                return
            }
            consumptionSummer.isBlank() -> {
                showInvalidInputConsumptionSummerMessage("Введите расход в летнее время")
                return
            }
            consumptionWinter.isBlank() -> {
                showInvalidInputConsumptionWinterMessage("Введите расход в зимнее время")
                return
            }
            fuelValue.isBlank() -> {
                showInvalidInputFuelValueMessage("Введите остаток топлива в баке")
                return
            }
            car == null -> {
                val newCar = Car(null, carName, carMileage, consumptionSummer, consumptionWinter, fuelValue)
                createCar(newCar)
            }
        }
    }


    private fun showInvalidInputCarNameMessage(text: String) = viewModelScope.launch {
        addCarEditChannel.send(AddEditCarEvent.ShowInvalidInputCarNameMessage(text))
    }
    private fun showInvalidInputCarMileageMessage(text: String) = viewModelScope.launch {
        addCarEditChannel.send(AddEditCarEvent.ShowInvalidInputCarMileageMessage(text))
    }
    private fun showInvalidInputConsumptionSummerMessage(text: String) = viewModelScope.launch {
        addCarEditChannel.send(AddEditCarEvent.ShowInvalidInputConsumptionSummerMessage(text))
    }
    private fun showInvalidInputConsumptionWinterMessage(text: String) = viewModelScope.launch {
        addCarEditChannel.send(AddEditCarEvent.ShowInvalidInputConsumptionWinterMessage(text))
    }
    private fun showInvalidInputFuelValueMessage(text: String) = viewModelScope.launch {
        addCarEditChannel.send(AddEditCarEvent.ShowInvalidInputFuelValueMessage(text))
    }


    sealed class AddEditCarEvent {
        data class ShowInvalidInputCarNameMessage(val msg: String): AddEditCarEvent()
        data class ShowInvalidInputCarMileageMessage(val msg: String): AddEditCarEvent()
        data class ShowInvalidInputConsumptionSummerMessage(val msg: String): AddEditCarEvent()
        data class ShowInvalidInputConsumptionWinterMessage(val msg: String): AddEditCarEvent()
        data class ShowInvalidInputFuelValueMessage(val msg: String): AddEditCarEvent()
        data class DismissDialog(val result: Int): AddEditCarEvent()


    }


}