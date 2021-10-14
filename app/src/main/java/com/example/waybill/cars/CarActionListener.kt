package com.example.waybill.cars

import com.example.waybill.data.model.Car

interface CarActionListener {
    fun removeCar(car: Car)
}