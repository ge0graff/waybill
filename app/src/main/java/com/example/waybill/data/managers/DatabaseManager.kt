package com.example.waybill.data.managers

import com.example.waybill.data.Database
import com.example.waybill.data.model.Car
import com.example.waybill.data.model.Waybills

class DatabaseManager(database: Database?) {

//  cars entity

    private val carsDao = database?.carsDao()

    fun carsDaoBuild() = carsDao != null

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

//  waybills entity

    private val waybillsDao = database?.waybillsDao()

    fun waybillsDaoBuild() = waybillsDao != null

    fun insertWaybills(waybills: Waybills) = waybillsDao?.insert(waybills)

    fun insertWaybills(waybills: List<Waybills>) {
        waybills.forEach { waybillsDao?.insert(it) }
    }

    fun updateWaybills(waybills: Waybills) = waybillsDao?.update(waybills)

    fun deleteWaybills(waybills: Waybills) = waybillsDao?.delete(waybills)

    fun deleteWaybills(waybills: List<Waybills>) {
        waybills.forEach { waybillsDao?.delete(it) }
    }

    fun getWaybills() = waybillsDao?.reedAllData()

    fun getWaybills(id: Int) = waybillsDao?.getById(id)

}