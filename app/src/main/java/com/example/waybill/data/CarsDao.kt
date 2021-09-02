package com.example.waybill.data


import androidx.room.*


@Dao
interface CarsDao {

    @Query("SELECT * FROM cars_table")
    fun reedAllData(): List<Cars>

    @Query("SELECT * FROM cars_table WHERE id = :id")
    fun getById(id: Long): List<Cars>

    @Insert
    fun insert (cars: Cars)

    @Update
    fun update (cars: Cars)

    @Delete
    fun delete (cars: Cars)


}