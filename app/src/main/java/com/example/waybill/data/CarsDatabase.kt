 package com.example.waybill.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Cars::class], version = 1, exportSchema = false)
abstract class CarsDatabase: RoomDatabase() {

    abstract fun carsDao(): CarsDao
}