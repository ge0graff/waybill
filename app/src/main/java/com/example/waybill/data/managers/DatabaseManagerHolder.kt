package com.example.waybill.data.managers

import android.content.Context
import androidx.room.Room
import com.example.waybill.data.Database

object CarsDatabaseManagerHolder {

    var carsDatabaseManager: CarsDatabaseManager = CarsDatabaseManager(null)
    get() {
        if (!field.isBuild()) throw Exception("Please build managers")
        else return field
    }

    fun buildManagers(context: Context) {
        carsDatabaseManager = CarsDatabaseManager(
            Room.databaseBuilder(
                context.applicationContext,
                Database::class.java,
                "car_table"
            )
                .allowMainThreadQueries()
                .build()
        )
    }

}

object ListDatabaseManagerHolder {

    var listDatabaseManager: ListDatabaseManager = ListDatabaseManager(null)
        get() {
            if (!field.isBuild()) throw Exception("Please build managers")
            else return field
        }

    fun buildManagers(context: Context) {
        listDatabaseManager = ListDatabaseManager(
            Room.databaseBuilder(
                context.applicationContext,
                Database::class.java,
                "car_table"
            )
                .allowMainThreadQueries()
                .build()
        )
    }

}