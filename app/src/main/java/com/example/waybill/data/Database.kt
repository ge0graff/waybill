 package com.example.waybill.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.waybill.data.dao.CarsDao
import com.example.waybill.data.dao.WaybillsDao
import com.example.waybill.data.model.Car
import com.example.waybill.data.model.Waybills
import com.example.waybill.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

 @Database(entities =
     [Car::class,
     Waybills::class],
     version = 1, exportSchema = false)

 abstract class Database: RoomDatabase() {
     abstract fun carsDao(): CarsDao
     abstract fun waybillsDao(): WaybillsDao

     class Callback @Inject constructor(
         private val database: Provider<com.example.waybill.data.Database>,
         @ApplicationScope private val applicationScope: CoroutineScope
     ): RoomDatabase.Callback(){

         override fun onCreate(db: SupportSQLiteDatabase) {
             super.onCreate(db)

             val carsDao = database.get().carsDao()
             val waybillDao = database.get().waybillsDao()

             applicationScope.launch {

             }
         }

     }

}
