package com.example.waybill.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars_table")
data class Cars (
                @PrimaryKey(autoGenerate = true)
                 val id: Long?,
                 val name: String,
                 val mileage: String,
                 val consumption_summer: String,
                 val consumption_winter: String,
                 val fuel_value: String

        )
