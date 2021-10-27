 package com.example.waybill.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.waybill.data.dao.CarsDao
import com.example.waybill.data.dao.WaybillsDao
import com.example.waybill.data.model.Car
import com.example.waybill.data.model.Waybills

 @Database(entities = [Car::class, Waybills::class], version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {
    abstract fun carsDao(): CarsDao
    abstract fun waybillsDao(): WaybillsDao
}