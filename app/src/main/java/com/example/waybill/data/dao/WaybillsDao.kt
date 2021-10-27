package com.example.waybill.data.dao

import androidx.room.*
import com.example.waybill.data.model.Waybills

@Dao
interface WaybillsDao {

    @Query("SELECT * FROM waybills_table")
    fun reedAllData(): List<Waybills>

    @Query("SELECT * FROM waybills_table WHERE id = :id")
    fun getById(id: Int): Waybills

    @Insert
    fun insert (list: Waybills)

    @Update
    fun update (list: Waybills)

    @Delete
    fun delete (list: Waybills)


}