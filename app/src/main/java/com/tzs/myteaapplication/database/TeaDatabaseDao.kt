package com.tzs.myteaapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TeaDatabaseDao {
    @Insert
    suspend fun insert(tea: Tea)

    @Update
    fun update (tea: Tea)

    @Query("SELECT * FROM tea_table where teaId = :key")
    fun get (key: Int): Tea

    @Query("SELECT * FROM tea_table")
    suspend fun getAllTea (): List<Tea>

    @Delete
    fun deleteTea(tea: Tea)
}
