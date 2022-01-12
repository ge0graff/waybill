package com.example.waybill.presentation.ui.fragments.waybillfragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.waybill.data.dao.WaybillsDao
import com.example.waybill.data.objects.DataObject
import com.example.waybill.data.objects.SelectedCar

class WaybillFragmentViewModel @ViewModelInject constructor (
    private val waybillsDao: WaybillsDao
        ): ViewModel() {

    private val carId = SelectedCar.id
    val waybills = waybillsDao.getCarWaybills(carId, DataObject.mouthId).asLiveData()

}