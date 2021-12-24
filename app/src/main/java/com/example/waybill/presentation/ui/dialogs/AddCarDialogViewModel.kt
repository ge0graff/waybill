package com.example.waybill.presentation.ui.dialogs

import android.app.Application
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.waybill.data.dao.CarsDao
import com.example.waybill.data.model.Car
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddCarDialogViewModel @ViewModelInject constructor(
    application: Application,
    private val carsDao: CarsDao,
    @Assisted private val state: SavedStateHandle
): AndroidViewModel(application) {

    fun createCar(car: Car) = viewModelScope.launch(Dispatchers.IO){
        carsDao.insert(car)
    }

}


