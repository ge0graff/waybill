package com.example.waybill

import android.app.Application
import com.example.waybill.data.managers.CarsDatabaseManagerHolder

class WayBillApp: Application() {

    override fun onCreate() {
        super.onCreate()
        CarsDatabaseManagerHolder.buildManagers(this)
    }
}