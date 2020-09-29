package com.tzs.myteaapplication.database

import androidx.room.*

@Dao
interface TeaDatabaseDao {
    @Insert
    fun insert (tea: Tea)

    @Update
    fun update (tea: Tea)

    @Query("SELECT * FROM tea_table where teaId = :key")
    fun get (key: Int): Tea

    @Query("SELECT * FROM tea_table")
    fun getAllTea (key: Int): Tea

    @Delete
    fun deleteTea(tea: Tea)
}
