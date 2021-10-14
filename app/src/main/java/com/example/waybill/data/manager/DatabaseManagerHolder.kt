package com.example.waybill.data.manager

import android.content.Context
import androidx.room.Room
import com.example.waybill.data.CarsDatabase

object DatabaseManagerHolder {

    var databaseManager: DatabaseManager = DatabaseManager(null)
    get() {
        if (!field.isBuild()) throw Exception("Please build managers")
        else return field
    }

    fun buildManagers(context: Context) {
        databaseManager = DatabaseManager(
            Room.databaseBuilder(
                context.applicationContext,
                CarsDatabase::class.java,
                "car_table"
            )
                .allowMainThreadQueries()
                .build()
        )
    }

}