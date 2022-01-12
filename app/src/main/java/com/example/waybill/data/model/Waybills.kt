package com.example.waybill.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "waybills_table")
data class Waybills (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val carId: Int?,
    val mileage: String,
    val dailyMileage: String,
    val fuelValue: String,
    val refueling: String,
    val mouth: String
)