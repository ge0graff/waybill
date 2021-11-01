package com.example.waybill.domain

import android.app.Application
import com.example.waybill.data.managers.DatabaseManagerHolder

class WayBillApp: Application() {

    override fun onCreate() {
        super.onCreate()
        DatabaseManagerHolder.buildDatabaseManagers(this)
//        WaybillsDatabaseManagerHolder.buildWaybillsDatabaseManagers(this)
    }
}