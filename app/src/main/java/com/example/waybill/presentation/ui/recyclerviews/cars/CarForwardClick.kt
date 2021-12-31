package com.example.waybill.presentation.ui.recyclerviews.cars

import com.example.waybill.data.model.Car

interface CarForwardClick {
    fun onCarDetails(car: Car)
    fun onCarSelect(car: Car)
    fun onLongClick(car: Car)
}