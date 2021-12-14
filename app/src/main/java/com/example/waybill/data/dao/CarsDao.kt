package com.example.waybill.data.dao

import androidx.room.*
import com.example.waybill.data.model.Car
import kotlinx.coroutines.flow.Flow


@Dao
interface CarsDao {

    @Query("SELECT * FROM cars_table")
    fun reedAllData(): Flow<List<Car>>

    @Query("SELECT * FROM cars_table WHERE id = :id")
    fun getById(id: Int): Car

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (car: Car)

    @Update
    suspend fun update (car: Car)

    @Delete
    suspend fun delete (car: Car)
}