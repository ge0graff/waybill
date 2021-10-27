package com.example.waybill.data.dao

import androidx.room.*
import com.example.waybill.data.model.Car


@Dao
interface CarsDao {

    @Query("SELECT * FROM cars_table")
    fun reedAllData(): List<Car>

    @Query("SELECT * FROM cars_table WHERE id = :id")
    fun getById(id: Int): Car

    @Insert
    fun insert (car: Car)

    @Update
    fun update (car: Car)

    @Delete
    fun delete (car: Car)
}