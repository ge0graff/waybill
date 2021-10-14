package com.example.waybill.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars_table")
data class Car (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    var name: String,
    var mileage: String,
    var consumption_summer: String,
    var consumption_winter: String,
    var fuel_value: String,
)
