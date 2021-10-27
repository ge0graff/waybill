package com.example.waybill.data.managers

import com.example.waybill.data.Database
import com.example.waybill.data.model.Car

class CarsDatabaseManager(database: Database?) {

    private val carsDao = database?.carsDao()

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

class ListDatabaseManager(database: Database?) {

    private val listsDao = database?.carsDao()

    fun isBuild() = listsDao != null

    fun insertCar(car: Car) = listsDao?.insert(car)

    fun insertCars(cars: List<Car>) {
        cars.forEach { listsDao?.insert(it) }
    }

    fun updateCar(car: Car) = listsDao?.update(car)

    fun deleteCar(car: Car) = listsDao?.delete(car)

    fun deleteCard(cars: List<Car>) {
        cars.forEach { listsDao?.delete(it) }
    }

    fun getCars() = listsDao?.reedAllData()

    fun getCar(id: Int) = listsDao?.getById(id)

}