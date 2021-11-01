package com.example.waybill.data.managers

import android.content.Context
import androidx.room.Room
import com.example.waybill.data.Database

object DatabaseManagerHolder {

    var databaseManager: DatabaseManager = DatabaseManager(null)
    get() {
        if (!field.carsDaoBuild() && !field.waybillsDaoBuild()) throw Exception("Please build managers")
        else return field
    }

    fun buildDatabaseManagers(context: Context) {
        databaseManager = DatabaseManager(
            Room.databaseBuilder(
                context.applicationContext,
                Database::class.java,
                "my_database"
            )
                .allowMainThreadQueries()
                .build()
        )
    }

}

//object WaybillsDatabaseManagerHolder {
//
//    var waybillsDatabaseManager: WaybillsDatabaseManager = WaybillsDatabaseManager(null)
//        get() {
//            if (!field.isBuild()) throw Exception("Please build managers")
//            else return field
//        }
//
//    fun buildWaybillsDatabaseManagers(context: Context) {
//        waybillsDatabaseManager = WaybillsDatabaseManager(
//            Room.databaseBuilder(
//                context.applicationContext,
//                Database::class.java,
//                "waybills_table"
//            )
//                .allowMainThreadQueries()
//                .build()
//        )
//    }
//
//}