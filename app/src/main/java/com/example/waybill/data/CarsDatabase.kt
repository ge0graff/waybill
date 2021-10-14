 package com.example.waybill.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.waybill.data.dao.CarsDao
import com.example.waybill.data.model.Car

 @Database(entities = [Car::class], version = 1, exportSchema = false)
abstract class CarsDatabase: RoomDatabase() {
    abstract fun carsDao(): CarsDao
}