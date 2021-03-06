package com.tzs.myteaapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TeaDatabaseDao {
    @Insert
    suspend fun insert(tea: TeaEntity)

    @Update
    suspend  fun update (tea: TeaEntity)

    @Query("SELECT * FROM tea_table where teaId = :key")
    suspend fun get (key: Int): TeaEntity

    @Query("SELECT * FROM tea_table")
    fun getAllTea (): LiveData<List<TeaEntity>>

    @Delete
    suspend fun deleteTea(tea: TeaEntity)
}
