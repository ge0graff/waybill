package com.example.waybill.presentation.ui.fragments.carsfragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.waybill.data.dao.CarsDao
import com.example.waybill.data.model.Car
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CarsFragmentViewModel @ViewModelInject constructor(
    private val carsDao: CarsDao
): ViewModel() {

    val cars = carsDao.reedAllData().asLiveData()

    private val carsTaskChannel = Channel<CarsEvent>()
    val carsEvent = carsTaskChannel.receiveAsFlow() // Вопрос про фло


    fun onRemoveCar(car: Car) = viewModelScope.launch(Dispatchers.IO){
        carsDao.delete(car)
        carsTaskChannel.send(CarsEvent.ShowUndoDeleteTaskMessage(car))
    }

    fun onUndoDeleteClick(car: Car) = viewModelScope.launch(Dispatchers.IO){
        carsDao.insert(car)
    }

    fun onAddCarClick() = viewModelScope.launch{
        carsTaskChannel.send(CarsEvent.NavigateToAddCarScreen)
    }

//    fun showDialog(manager: Fragment, adapter: CarsAdapter){
//        val dialog = AddCarDialogFragment(adapter)
//        dialog.show(manager.parentFragmentManager, "customDialog")
//    }

    sealed class CarsEvent{
        object NavigateToAddCarScreen: CarsEvent()
        data class ShowUndoDeleteTaskMessage(val car: Car): CarsEvent()
    }


}


