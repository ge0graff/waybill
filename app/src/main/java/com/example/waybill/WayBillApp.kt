package com.example.waybill

import android.app.Application
import com.example.waybill.data.manager.DatabaseManagerHolder

class WayBillApp: Application() {

    override fun onCreate() {
        super.onCreate()
        DatabaseManagerHolder.buildManagers(this)
    }
}