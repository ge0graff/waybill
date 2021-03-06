package com.example.waybill.data.dao

import androidx.room.*
import com.example.waybill.data.model.Waybills
import kotlinx.coroutines.flow.Flow

@Dao
interface WaybillsDao {

    @Query("SELECT * FROM waybills_table")
    fun reedAllData(): Flow<List<Waybills>>

    @Query("SELECT * FROM waybills_table WHERE id = :id")
    fun getById(id: Int): Flow<Waybills>

    @Query("SELECT * FROM waybills_table WHERE carId = :id AND mouth = :mouth")
    fun getCarWaybills(id: Int, mouth: String): Flow<List<Waybills>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (list: Waybills)

    @Update
    suspend fun update (list: Waybills)

    @Delete
    suspend fun delete (list: Waybills)


}