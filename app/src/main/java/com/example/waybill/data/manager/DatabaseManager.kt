package com.example.waybill.data.manager

import com.example.waybill.data.CarsDatabase
import com.example.waybill.data.model.Car

class DatabaseManager(carsDatabase: CarsDatabase?) {

    private val carsDao = carsDatabase?.carsDao()

    fun isBuild() = carsDao != null

    fun insertCar(car: Car) = carsDao?.insert(car)

    fun insertCars(cars: List<Car>) {
        cars.forEach { carsDao?.insert(it) }
    }

    fun updateCar(car: Car) = carsDao?.update(car)

    fun deleteCar(car: Car) = carsDao?.delete(car)

    fun deleteCard(cars: List<Car>) {
        cars.forEach { carsDao?.delete(it) }
    }

    fun getCars() = carsDao?.reedAllData()

    fun getCar(id: Int) = carsDao?.getById(id)

}